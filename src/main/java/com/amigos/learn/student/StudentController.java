package com.amigos.learn.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService=studentService;
    }


    @GetMapping()
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping()
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id")Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping("{id}")
    public Student updateStudent(
        @PathVariable("id")Long id,
        //lezem tbadelhom bech twali tekhouhom mel body
        @RequestParam("name") String name,
        @RequestParam("email")String email
    ){
        System.out.println("name"+name);
        System.out.println("email:"+email);
        return studentService.updateStudent(id,name,email);
    }

}
