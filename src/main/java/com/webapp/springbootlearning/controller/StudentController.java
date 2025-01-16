package com.webapp.springbootlearning.controller;

import com.webapp.springbootlearning.entity.Student;
import com.webapp.springbootlearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomepage(Model model){
        List<Student> studentList = studentService.listAll();
        model.addAttribute("liststudent",studentList);
        System.out.println("home page");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("student",new Student());
        return "new";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentpage(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("new");
        Student student = studentService.get(id);
        mav.addObject("student",student);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deletestudent(@PathVariable Long id){
        studentService.delstudent(id);
        return "redirect:/";
    }
    


}
