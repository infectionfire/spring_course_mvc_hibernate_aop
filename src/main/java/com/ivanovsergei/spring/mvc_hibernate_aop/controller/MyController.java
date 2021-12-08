package com.ivanovsergei.spring.mvc_hibernate_aop.controller;

import com.ivanovsergei.spring.mvc_hibernate_aop.entity.Employee;
import com.ivanovsergei.spring.mvc_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MyController {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping("/")
    public String showAllEmployees(Model model){

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);

        return "all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model){
    //с помощью нот аргс конструктора передаем пустого работника, поля заполнить для добавления
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee-info";

    }
    @RequestMapping("/saveEmployee")//метод будет выделяться и при добавлении и при редактировании
    public String saveEmployee(@ModelAttribute("employee") Employee employee){


            employeeService.saveEmployee(employee);//сохраняем работника с данными


        return "redirect:/";
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model){

        Employee employee = employeeService.getEmployee(id);//получаем работника по ид
        model.addAttribute("employee", employee);

        return "employee-info";
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id, Model model){//

        employeeService.deleteEmployee(id);

        return "redirect:/";//возвращаем список работников
    }

}
