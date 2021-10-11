package com.example.mongosearchproject.service;


import com.example.mongosearchproject.data.document.Address;
import com.example.mongosearchproject.data.document.Department;
import com.example.mongosearchproject.data.document.Employee;
import com.example.mongosearchproject.data.dto.DepartmentAddressDto;
import com.example.mongosearchproject.data.dto.EmployeeDto;
import com.example.mongosearchproject.data.search.Filter;
import com.example.mongosearchproject.data.search.dto.Search;
import com.example.mongosearchproject.repository.AddressRepo;
import com.example.mongosearchproject.repository.DepartmentRepo;
import com.example.mongosearchproject.repository.EmployeeRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        Department department = departmentRepo.findById(depId).get();
        mongoTemplate.save(employee, "employee");
        department.getEmployees().add(employee);
        mongoTemplate.save(department, "department");
        System.out.println();
    }

    @Override
    public List<Department> search(Search search) {

        Criteria name = null;
        if (search.getName() != null) {
            name = new Criteria("name").is(search.getName());

        }
        Criteria year = null;
        if (search.getYearFrom() != null && search.getYearTo() != null) {
            year = new Criteria("yearOfCreation").gte(search.getYearFrom()).lte(search.getYearTo());
        } else if (search.getYearFrom() != null && search.getYearTo() == null) {
            year = new Criteria("yearOfCreation").gte(search.getYearFrom());
        } else if (search.getYearFrom() == null && search.getYearTo() != null) {
            year = new Criteria("yearOfCreation").lte(search.getYearTo());
        }
        Criteria zip = null;
        if (search.getZipCode() != null) {
            zip = new Criteria("address.zipCode")
                    .is(search.getZipCode());
        }
        Criteria street = null;
        if (search.getStreet() != null) {
            street = new Criteria("address.street").is(search.getStreet());
        }

        Criteria city = null;
        if (search.getCity() != null) {
            city = new Criteria("address.city")
                    .is(search.getCity());
        }

        List<Criteria> keywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(search.getCountryKeywords())) {
            for (String key : search.getCountryKeywords()) {
                Criteria criteria = new Criteria("address.country")
                        .regex("/.*" + key + ".*/");
                keywords.add(criteria);
            }
        }// is empty


        TextCriteria firstLastNames =
                null;
        if (search.getTextIndexItem1() != null || search.getTextIndexItem2() != null) {
            firstLastNames = new TextCriteria()
                    .matchingAny(search.getTextIndexItem1(), search.getTextIndexItem2());
        }

        Criteria age = null;
        if (search.getAge() != null) {
            age = new Criteria("employees.age")
                    .is(search.getAge());
        }
        Criteria username = null;
        if (search.getUsername() != null) {
            username = new Criteria("employees.username")
                    .is(search.getUsername());
        }
        Criteria salary = null;
        if (search.getSalaryFrom() != null && search.getSalaryTo() != null) {
            salary = new Criteria("employees.salary").gte(search.getSalaryFrom()).lte(search.getSalaryTo());
        } else if (search.getSalaryFrom() != null && search.getSalaryTo() == null) {
            salary = new Criteria("employees.salary").gte(search.getSalaryFrom());
        } else if (search.getSalaryFrom() == null && search.getSalaryTo() != null) {
            salary = new Criteria("employees.salary").lte(search.getSalaryTo());
        }//can be null

        List<Criteria> criteriaList = Arrays.asList(name, year, zip, street, city, age, salary, username);
        if (!CollectionUtils.isEmpty(keywords)) {
            keywords.forEach(criteriaList::add);
        }

        List<Criteria> criteriaListFinal = filter.nonNull(criteriaList);

        Query query = null;
        if (!CollectionUtils.isEmpty(criteriaListFinal)) {
            for (Criteria criteria : criteriaListFinal)
                query = new Query()
                        .addCriteria(criteria);
        }
        if (firstLastNames != null) {
            query = new Query().addCriteria(firstLastNames);
        }


        return mongoTemplate.find(query, Department.class);
    }
}
