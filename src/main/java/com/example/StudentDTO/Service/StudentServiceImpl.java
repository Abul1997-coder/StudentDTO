package com.example.StudentDTO.Service;

import com.example.StudentDTO.DTO.StudentRequestDTO;
import com.example.StudentDTO.DTO.StudentResponceDTO;
import com.example.StudentDTO.Enitity.Student;
import com.example.StudentDTO.Repository.StudentRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@Data       // @Getter and @Setter
public class StudentServiceImpl implements StudentServiceInterface {

  private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

//Create or Add new Student
    @Override
    public StudentResponceDTO createStudent(StudentRequestDTO dto) {

        //DTO-> Entity
        Student student = new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setSalary(dto.getSalary());
        student.setPassword(dto.getPassword());
        Student saveStudent = repository.save(student);


        // Entity -> Response DTO

        StudentResponceDTO responce = new StudentResponceDTO();
        responce.setId(saveStudent.getId());
        responce.setName(saveStudent.getName());
        responce.setEmail(saveStudent.getEmail());
        responce.setDepartment(saveStudent.getDepartment());

        return responce;
    }

    //Fetch All Student
    @Override
    public List<StudentResponceDTO> getAll() {
        return repository.findAll()
                .stream()                             //Stream is used to process collections
                .map(student -> {              //Map Used to convert one object into another object.
                    StudentResponceDTO dto = new StudentResponceDTO();     // Create a new  dto object

                    dto.setId(student.getId());      //Gets ID from Student entity. and Set Id in DTO(Copy data from Entity to DTO and store in DTO)
                    dto.setName(student.getName());
                    dto.setEmail(student.getEmail());
                    dto.setDepartment(student.getDepartment());    //collect() converts Stream back into List.
                    return  dto;

                })
                .collect(Collectors.toList());
    }



    //Fetch by Id
    @Override
    public StudentResponceDTO getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(()->new RuntimeException("Student Not Found"));

    StudentResponceDTO dto = new StudentResponceDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setEmail(student.getEmail());
    dto.setDepartment(student.getDepartment());

        return dto;
    }

    @Override
    public void deleteByid(Long id) {
        repository.deleteById(id);

    }
}
