package sg.edu.nus.iss.day13_lecture.repository;

import org.springframework.stereotype.Repository;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import sg.edu.nus.iss.day13_lecture.model.*;


@Repository
public class EmployeeRepo {
    
    final String dirPath = "/Users/Darryl/data";
    final String filename = "employee.txt";

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

    public Boolean save(Employee employee) throws FileNotFoundException{
        Boolean result = employees.add(employee);

        File f = new File(dirPath + "/" + filename);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

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

    public Employee findByEmailId(String email) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equals(email)).findFirst().get();

        return emp;
    }

    public Boolean updateEmployee(Employee em) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equals(em.getEmail())).findFirst().get();

        int employeeIndex = employees.indexOf(emp);

        if (employeeIndex >= 0) {
            // employees.remove(employeeIndex);

            employees.get(employeeIndex).setAddress(em.getAddress());
            employees.get(employeeIndex).setBirthDay(em.getBirthDay());
            employees.get(employeeIndex).setFirstName(em.getFirstName());
            employees.get(employeeIndex).setLastName(em.getLastName());
            employees.get(employeeIndex).setSalary(em.getSalary());
            employees.get(employeeIndex).setPhoneNo(em.getPhoneNo());
            employees.get(employeeIndex).setPostalCode(em.getPostalCode());
        }

        // employees.add(em);

        return true;
    }
}
