package com.example.StudentDTO.DTO;

import lombok.Data;

@Data       // @Getter and @Setter
public class StudentResponceDTO {

    private Long id;
    private String name;
    private String email;
    private String department;



    // Use Getter and Setter

}
/*
* What is StudentResponseDTO?

StudentResponseDTO is used to send data back to the client.

Example Response:

{
  "id": 1,
  "name": "Abul",
  "email": "abul@gmail.com",
  "department": "IT"
}

Only required fields are returned.
*
*
* Why do we use DTO?
Hide sensitive data (password, salary).
Send only required fields to the client.
Separate API layer from Entity layer.
Improve security and maintainability.
* */