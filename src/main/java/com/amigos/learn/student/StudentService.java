package com.amigos.learn.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student addStudent(Student student){
        Optional<Student> studentByEmail =studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }


    public void deleteStudent(Long id) {
        System.out.println(id);
        try{
            studentRepository.deleteById(id);
        }
        catch (Exception e){
            throw new IllegalStateException("Student with this id not found");
        }
    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        Student student=studentRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("Student does not exists")
        );

        if (name!=null && !name.isEmpty()){
            student.setName(name);
        }

        //need more validation if the email exists
        if (email!=null && !email.isEmpty() && !email.equals(student.getEmail())){
            student.setEmail(email);
        }

        return student;
    }
}
