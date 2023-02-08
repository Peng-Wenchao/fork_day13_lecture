package sg.edu.nus.iss.day13_lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.*;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;
import sg.edu.nus.iss.day13_lecture.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);

        return "employeeslist";
    }

    @GetMapping("/addnew")
    public String addPage(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

}
