package com.example.StudentDTO.Controller;

import com.example.StudentDTO.DTO.StudentRequestDTO;
import com.example.StudentDTO.DTO.StudentResponceDTO;
import com.example.StudentDTO.Service.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl service;


    public StudentController(StudentServiceImpl service) {
        this.service = service;
    }


    //Create Student

    @PostMapping("/create")
    public StudentResponceDTO createStudent(@RequestBody StudentRequestDTO dto){
        return service.createStudent(dto);
    }

    //Fetch All Student
    @GetMapping("/getAll")
    public List<StudentResponceDTO> getAllStudent()
    {

        return service.getAll();
    }

    //Fetch by Id
    @GetMapping("/getid/{id}")
    public  StudentResponceDTO getById(@PathVariable Long id)
    {
        return service.getStudentById(id);
    }


    //Delete by Id
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        service.deleteByid(id);
        return "Student Deleted Sucessfully";
    }

}
