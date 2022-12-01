package dev.rest.controllers;

import dev.rest.dto.PersonDTO;
import dev.rest.models.Person;
import dev.rest.services.PeopleService;
import dev.rest.util.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    public AuthController(PersonValidator personValidator, PeopleService peopleService, ModelMapper modelMapper){
        this.personValidator=personValidator;
        this.peopleService=peopleService;
        this.modelMapper=modelMapper;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person")PersonDTO personDTO){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult){
        personValidator.validate(personDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return "auth/registration";
        }

        peopleService.registration(convertToPerson(personDTO));
        return "redirect:/login";
    }

    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
