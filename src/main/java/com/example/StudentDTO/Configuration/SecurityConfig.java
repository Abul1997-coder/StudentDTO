package com.example.StudentDTO.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        // Admin User
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        // Normal User
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                // Disable CSRF for Postman
                .csrf(csrf -> csrf.disable())

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        //No need required login Authentication becuse this is public(.permitAll())
                        //login and register for everyone
                        .requestMatchers("/login", "/register").permitAll()


                        // Only ADMIN can delete
                       // .requestMatchers("/student/getAll").hasRole("ADMIN")

                        // means all GET APIs under /students can be accessed by users and admin
                        // view the data by user and admin
                        .requestMatchers(HttpMethod.GET,"/student/getAll").hasAnyRole("USER","ADMIN")

                        // USER and ADMIN can view
                       //.requestMatchers("/student").hasAnyRole("ADMIN", "USER")

                        //means all Post APIs under /students can be accessed by admin
                        // // create or add new  data by user and admin
                        .requestMatchers(HttpMethod.POST,"/student/create").hasAnyRole("USER","ADMIN")

                        //Ony deleted by Admin
                        .requestMatchers(HttpMethod.DELETE,"/student/delete").hasAnyRole("ADMIN")


                        //Must be logged in (authentication required)
                        .anyRequest().authenticated()
                )

                // Enable Login Form
                .formLogin(form -> form.permitAll())

                // Enable Basic Auth
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}










/*
view the data by user and admin
.requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("USER", "ADMIN")

means all GET APIs under /students can be accessed by users having either USER role or ADMIN role
/students
/students/1
/students/10
/students/department/IT


create or add new  data by user and admin
.requestMatchers(HttpMethod.POST,"/student/create").hasAnyRole("USER","ADMIN")





It is used to choose which URL (endpoint) the security rule should apply to
.requestMatchers()
 */










/*
* Request	Result
GET /login	Allowed for everyone
DELETE /student/delete/1	Must have ADMIN role
GET /students	Must be authenticated
GET /profile	Must be authenticated
Can .anyRequest().authenticated() cause 405?



401 → Not authenticated
403 → Authenticated but not authorized
405 → Wrong HTTP method for the endpoint
* */