package com.example.StudentDTO.Controller;

import com.example.StudentDTO.DTO.StudentRequestDTO;
import com.example.StudentDTO.DTO.StudentResponceDTO;
import com.example.StudentDTO.Service.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/student")
public class StudentController {


    //Logger Object
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentServiceImpl service;


    public StudentController(StudentServiceImpl service) {
        this.service = service;
    }


    //Create Student===================

    @PostMapping("/create")
    public StudentResponceDTO createStudent(@RequestBody StudentRequestDTO dto){

        log.info("Post /Student API Called");            // Log when API is called

        //return service.createStudent(dto);
        StudentResponceDTO responceDTO = service.createStudent(dto);

        log.info("Controller Student created Sucessfully with Id: {}",responceDTO.getId());    // Log after successful creation
        return responceDTO;
    }

    //Fetch All Student=================
    @GetMapping("/getAll")
    public List<StudentResponceDTO> getAllStudent()
    {
        log.info("GET All /getAll API Called");

        return service.getAll();
    }

    //Fetch by Id===================
    @GetMapping("/getid/{id}")
    public  StudentResponceDTO getById(@PathVariable Long id)
    {
        log.info("GET by id  /getid API Called: {}",id);
        return service.getStudentById(id);
    }


    //Delete by Id=======================
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){

        log.info("DELETE by id /delete/id: {}",id);
        service.deleteByid(id);

        return "Student Deleted Sucessfully";
    }

}
