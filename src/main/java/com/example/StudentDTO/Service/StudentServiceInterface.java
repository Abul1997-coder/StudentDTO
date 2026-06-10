package com.example.StudentDTO.Service;

import com.example.StudentDTO.DTO.StudentRequestDTO;
import com.example.StudentDTO.DTO.StudentResponceDTO;

import java.util.List;

public interface StudentServiceInterface {

    StudentResponceDTO createStudent(StudentRequestDTO dto);

    List<StudentResponceDTO> getAll();

    StudentResponceDTO getStudentById(Long id);

    void deleteByid(Long id);


}


/*
*
* Why Interface?

Acts as a contract.

Controller talks to Service interface.

Not directly to Service implementation.
*
* */