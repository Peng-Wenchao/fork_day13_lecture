package sg.edu.nus.iss.day13_lecture.repository;

import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import sg.edu.nus.iss.day13_lecture.model.*;


@Repository
public class EmployeeRepo {
    
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1980-10-15");
        Employee emp = new Employee("Derrick", "Tan", "derrick@gmail.com", "91234567", 7500, dt, "10 Ghim Moh", 272210);
        employees.add(emp);

        dt = df.parse("1979-02-18");
        emp = new Employee("Dennis", "Chew", "dennis@gmail.com", "91234567", 8500, dt, "28 Ghim Moh", 272228);
        employees.add(emp);
    }

    public List<Employee> findAll() {

        return employees;
    }

    public Boolean save(Employee employee) {

        Boolean result = employees.add(employee);
        return result;
    }

    public Boolean delete(Employee employee) {
        // Employee e = employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get();

        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }

        return result;
    }

}
