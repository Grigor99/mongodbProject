package com.example.mongosearchproject.service;


import com.example.mongosearchproject.data.document.Address;
import com.example.mongosearchproject.data.document.Department;
import com.example.mongosearchproject.data.document.Employee;
import com.example.mongosearchproject.data.dto.DepartmentAddressDto;
import com.example.mongosearchproject.data.dto.EmployeeDto;
import com.example.mongosearchproject.data.search.Filter;
import com.example.mongosearchproject.data.search.dto.Search;
import com.example.mongosearchproject.data.search.response.SearchResult;
import com.example.mongosearchproject.migration.Logs;
import com.example.mongosearchproject.repository.AddressRepo;
import com.example.mongosearchproject.repository.DepartmentRepo;
import com.example.mongosearchproject.repository.EmployeeRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private MongoTemplate mongoTemplate;
    private AddressRepo addressRepo;
    private Filter filter;
    private EmployeeRepo employeeRepo;
    private DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(MongoTemplate mongoTemplate, AddressRepo addressRepo, Filter filter, EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
        this.mongoTemplate = mongoTemplate;
        this.addressRepo = addressRepo;
        this.filter = filter;
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;

    }

    @Override
    public void createDepartment(DepartmentAddressDto dto) {
        Department department = new Department();
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setZipCode(dto.getZipCode());
        address.setStreet(dto.getStreet());
//        addressRepo.save(address);
        mongoTemplate.save(address, "address");
        department.setAddress(address);
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setYearOfCreation(dto.getYearOfCreation());
//        departmentRepo.save(department);
        mongoTemplate.save(department, "department");

    }

    @Override
    public void createEmployee(EmployeeDto dto, String depId) {
        Employee employee = new Employee();
        employee.setDepartmentId(depId);
        employee.setAge(dto.getAge());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setUsername(dto.getUsername());
        employee.setSalary(dto.getSalary());
        mongoTemplate.save(employee, "employee");

    }

    @Override
    public List<SearchResult> search(Search search) {
        Criteria name = Criteria.where("name")
                .is(search.getName());
        Criteria year = null;
        if (search.getYearFrom() != null && search.getYearTo() != null) {
            year = Criteria.where("yearOfCreation").gte(search.getYearFrom()).lte(search.getYearTo());
        } else if (search.getYearFrom() != null && search.getYearTo() == null) {
            year = Criteria.where("yearOfCreation").gte(search.getYearFrom());
        } else if (search.getYearFrom() == null && search.getYearTo() != null) {
            year = Criteria.where("yearOfCreation").lte(search.getYearTo());
        }
        Criteria zip = Criteria.where("zipCode")
                .is(search.getZipCode());
        Criteria street = Criteria.where("street").is(search.getStreet());
        Criteria city = Criteria.where("city")
                .is(search.getCity());

        List<Criteria> keywords = new ArrayList<>();
        for (String key : search.getCountryKeywords()) {
            Criteria criteria = Criteria.where("country")
                    .regex("/.*" + key + ".*/");
            keywords.add(criteria);
        }

        TextCriteria firstLastNames = TextCriteria.forDefaultLanguage()
                .matchingAny(search.getFirstName(), search.getLastName());

        Criteria age = Criteria.where("age")
                .is(search.getAge());
        Criteria username = Criteria.where("username")
                .is(search.getUsername());
        Criteria salary = null;
        if (search.getSalaryFrom() != null && search.getSalaryTo() != null) {
            year = Criteria.where("salary").gte(search.getSalaryFrom()).lte(search.getSalaryTo());
        } else if (search.getSalaryFrom() != null && search.getSalaryTo() == null) {
            year = Criteria.where("salary").gte(search.getSalaryFrom());
        } else if (search.getSalaryFrom() == null && search.getSalaryTo() != null) {
            year = Criteria.where("salary").lte(search.getSalaryTo());
        }
        List<Criteria> criteriaList = Arrays.asList(name, year, zip, street, city, age, salary, username);
        keywords.forEach(criteriaList::add);

        Query query = null;
        for (Criteria criteria : filter.nonNull(criteriaList))
            query = new Query()
                    .addCriteria(firstLastNames)
                    .addCriteria(criteria)
                    .with(Sort.by(Sort.Direction.ASC));

        return mongoTemplate.find(query, SearchResult.class);
    }
}
