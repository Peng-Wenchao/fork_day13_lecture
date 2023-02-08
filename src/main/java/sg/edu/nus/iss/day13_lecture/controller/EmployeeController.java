package sg.edu.nus.iss.day13_lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.*;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;
import sg.edu.nus.iss.day13_lecture.model.Employee;

import java.io.FileNotFoundException;

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

    @PostMapping("/saveEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model model) throws FileNotFoundException {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean bResult = false;
        bResult = empRepo.save(employeeForm);

        return "redirect:/employees/home";
    }

    @GetMapping("/deleteEmployee/{email}")
    public String delEmployee(@PathVariable("email") String email) {

        Employee emp = empRepo.findByEmailId(email);

        Boolean bResult = empRepo.delete(emp);

        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updEmployee(@PathVariable("email") String email, Model model) {
        Employee emp = empRepo.findByEmailId(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";
    } 

    @PostMapping("/updateEmp")
    public String updEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "employeeupdate";
        }

        empRepo.updateEmployee(emp);
        return "redirect:/employees/home";
    }

}
