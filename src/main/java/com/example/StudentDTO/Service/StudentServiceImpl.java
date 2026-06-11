package com.example.StudentDTO.Service;

import com.example.StudentDTO.DTO.StudentRequestDTO;
import com.example.StudentDTO.DTO.StudentResponceDTO;
import com.example.StudentDTO.Enitity.Student;
import com.example.StudentDTO.Repository.StudentRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Slf4j
@Service
@Data       // @Getter and @Setter
public class StudentServiceImpl implements StudentServiceInterface {


    //Loger Object
    private static final Logger log  = LoggerFactory.getLogger(StudentServiceImpl.class);

  private final StudentRepository repository;   // Value is final cannot be change

    public StudentServiceImpl(StudentRepository repository) {   // Contructor Dependency Injection
        this.repository = repository;
    }

//Create or Add new Student
    @Override
    public StudentResponceDTO createStudent(StudentRequestDTO dto) {

        log.info("Cretaing Student");

        //Request DTO-> Entity
        Student student = new Student();


        student.setName(dto.getName());
        log.info("Student Data recived", dto.getName());
        student.setEmail(dto.getEmail());
        student.setDepartment(dto.getDepartment());
        student.setSalary(dto.getSalary());
        student.setPassword(dto.getPassword());

        Student saveStudent = repository.save(student);
        log.info("Student saved sucessfully. id: {}", saveStudent);


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

        log.info("fatching all student from database");
        return repository.findAll()
                .stream()                             //Stream is used to process collections
                .map(student -> {              //Map Used to convert one object into another object.

                    log.debug("Processing Student ID: {}",student.getId() );
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

        log.info("Fetching Student by ID: {}",id);

        Student student = repository.findById(id)
                .orElseThrow(()->new RuntimeException("Student Not Found"));
                log.error("Student not Found witg Id: {}",id);

    StudentResponceDTO dto = new StudentResponceDTO();
    dto.setId(student.getId());

    log.info("Student found: {}",student.getId());
    dto.setName(student.getName());
    dto.setEmail(student.getEmail());
    dto.setDepartment(student.getDepartment());

        return dto;
    }

    @Override
    public void deleteByid(Long id) {
        log.info("Deleteing Student with Id: {}", id);
        repository.deleteById(id);
        log.info("Student deleted SucessFully with Id: {}",id);

    }
}


/*logger.info("Information message");
logger.debug("Debug message");
logger.warn("Warning message");
logger.error("Error message");
* */