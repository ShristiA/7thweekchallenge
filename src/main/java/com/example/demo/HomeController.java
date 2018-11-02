package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String homepage(){
        return "Index";
    }

    @RequestMapping("/adddepartment")
    public String getdepartmentform(Model model){
        model.addAttribute("department", new Department());
        return"adddepartment";
    }

    @PostMapping("/adddepartment")
    public String processdepartmentform(@ModelAttribute("department") Department department){
        departmentRepository.save(department);
        return "redirect:/alldepartments";
    }
    @GetMapping("/alldepartments")
    public String showalldepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return"alldepartments";
    }
    @RequestMapping("/addemployee")
    public String getemployeeform(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "addemployee";
    }

    @PostMapping("/addemployee")
    public String processemployeeform(@ModelAttribute("employee") Employee employee){
        employeeRepository.save(employee);
        return "redirect:/allemployees";
    }
    @GetMapping("/allemployees")
    public String getemployeeslist(Model model){
        model.addAttribute("employees",employeeRepository.findAll());
        return "allemployees";
    }
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "addemployee";
    }
    @RequestMapping("/detail/{id}")
    public String getEmployeeDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeedetail";
    }
    @RequestMapping("/delete/{id}")
    public String delEmployee(@PathVariable("id") long id){
      employeeRepository.deleteById(id);
        return "redirect:/allemployees";
    }


}
