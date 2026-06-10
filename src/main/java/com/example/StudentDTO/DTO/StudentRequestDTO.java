package com.example.StudentDTO.DTO;


import lombok.Data;

@Data       // @Getter and @Setter
public class StudentRequestDTO {

    private Long id;
    private String name;
    private String email;
    private String department;
    private Double salary;
    private String password;

    //Getter and Setter
}


/*
* What is StudentRequestDTO?

StudentRequestDTO is used to receive data from the client (Postman/UI).

Example:

{
  "name": "Abul",
  "email": "abul@gmail.com",
  "department": "IT",
  "salary": 50000,
  "password": "abc123"
}

This JSON is converted into a StudentRequestDTO object.
*
*  Why do we use DTO?
Hide sensitive data (password, salary).
Send only required fields to the client.
Separate API layer from Entity layer.
Improve security and maintainability.
* */
