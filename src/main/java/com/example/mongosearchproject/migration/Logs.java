package com.example.mongosearchproject.migration;

import com.example.mongosearchproject.data.document.Employee;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Setter
@Getter
@NoArgsConstructor
@ChangeLog(order = "001")
public class Logs {

    @ChangeSet(order = "001", id = "create department collection", author = "mongock")
    public void createDepartmentCollection(MongockTemplate db) {
        if (!db.collectionExists("department")) {
            db.createCollection("department");
        }
    }

    @ChangeSet(order = "002", id = "create address collection", author = "mongock")
    public void createAddressCollection(MongockTemplate db) {
        if (!db.collectionExists("address")) {
            db.createCollection("address");
        }
    }

    @ChangeSet(order = "003", id = "create employee collection", author = "mongock")
    public void createEmployeeCollection(MongockTemplate db) {
        if (!db.collectionExists("employee")) {
            db.createCollection("employee");
        }
        //this is how to find and modify
//        Query query = new Query();
//        query.addCriteria(Criteria.where("firstName").in("John"));
//        Update update = Update.update("lastName", "Jack");
//        db.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Employee.class);

//        db.findAndModify()
    }


    @ChangeSet(order = "004", id = "create employee", author = "mongock")
    public void createEmployee(MongockTemplate mongockTemplate) {
        Employee employee = new Employee();
        employee.setAge(55);
        employee.setFirstName("Jack");
        employee.setLastName("John");
        employee.setSalary(550000.5);
        employee.setUsername("employeeJack@gmail.com");
        employee.setDepartmentId("54323424242424");
        mongockTemplate.save(employee, "employee");
    }

    @ChangeSet(order = "005", id = "update all employees salaries", author = "mongock")
    public void updateEmployeesSalaries(MongockTemplate mongockTemplate) {

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Jack").and("lastName").is("John").and("salary")
                .is(550000.5).and("departmentId").is("54323424242424"));
        Update update = Update.update("salary", 660000);
        mongockTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Employee.class);
    }
    @ChangeSet(order = "006", id = "update all employees salaries again", author = "mongock")
    public void deleteJacks(MongockTemplate mongockTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Jack").and("lastName").is("John").and("salary")
                .is(660000).and("departmentId").is("54323424242424"));
        Update update = Update.update("salary", 880000);
        mongockTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Employee.class);
    }
    @ChangeSet(order = "007", id = "update all employees salaries option false", author = "mongock")
    public void upAgain(MongockTemplate mongockTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Jack").and("lastName").is("John").and("salary")
                .is(880000).and("departmentId").is("54323424242424"));
        Update update = Update.update("salary", 9000000);
        mongockTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(false), Employee.class);
    }

    @ChangeSet(order = "008", id = "update all employees salaries upsert", author = "mongock")
    public void upAggggain(MongockTemplate mongockTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Jack").and("lastName").is("John").and("salary")
                .is(9500000).and("departmentId").is("54323424242424"));
        Update update = Update.update("salary", 9300000);//only this given fields cretaed
        mongockTemplate.findAndModify(query, update, FindAndModifyOptions.options().upsert(true), Employee.class);
    }
    @ChangeSet(order = "009", id = "update all employee delete", author = "mongock")
    public void del(MongockTemplate mongockTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Jack").and("lastName").is("John").and("salary")
                .is(9300000).and("departmentId").is("54323424242424"));
        Update update = Update.update("salary", 9300000);//only this given fields cretaed
        mongockTemplate.findAndModify(query, update, FindAndModifyOptions.options().remove(true), Employee.class);
    }
}
