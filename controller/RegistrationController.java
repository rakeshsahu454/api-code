package com.apidemo.controller;

import com.apidemo.entity.Registration;
import com.apidemo.service.RegistrationDto;
import com.apidemo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    //http://localhost:8080/api/v1/register
    @PostMapping
    public ResponseEntity<?> createRegistration(
         @Valid @RequestBody RegistrationDto registrationDto,
         BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        RegistrationDto registration = registrationService.createRegistration(registrationDto);
       // return new ResponseEntity<>(registration, HttpStatus.CREATED );

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Custom-Header","value")
                .body(registration);


    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
            @RequestParam long id

    ) {
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Registration deleted successfully with ID: " + id,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/register/2
    @PutMapping("{id}")
    public String updateRegistration(
            @PathVariable long id,
            @RequestBody RegistrationDto registrationDto
    ) {
        registrationService.updateRegistration(id, registrationDto);
        return "Registration updated successfully with ID: " + id;
    }
    //http://localhost:8080/api/v1/register?pageNo=1&pageSize=3&sortBy=name
   @GetMapping
   public ResponseEntity<List<RegistrationDto>> getAllRegistrations(
       @RequestParam(name = "pageNo",required = false,defaultValue = "0") int pageNo,
       @RequestParam(name = "pageSize",required = false,defaultValue = "3")  int pageSize,
       @RequestParam(name="sortDir",required = false,defaultValue = "asc") String sortDir, @RequestParam(name="sortBy",required = false,defaultValue = "id") String sortBy
   ) {

     List<RegistrationDto> registrations = registrationService.getAllRegistrations(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(registrations, HttpStatus.OK);

    }
    @GetMapping("/byId")
    public ResponseEntity<Registration> getRegistrationById(
            @RequestParam long id) {
        Registration reg = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(reg, HttpStatus.OK);
    }

}


















