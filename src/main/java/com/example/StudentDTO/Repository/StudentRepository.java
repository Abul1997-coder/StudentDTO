package com.example.StudentDTO.Repository;

import com.example.StudentDTO.Enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student, Long> {
}
