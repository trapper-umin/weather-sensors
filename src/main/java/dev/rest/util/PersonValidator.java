package dev.rest.util;

import dev.rest.models.Person;
import dev.rest.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator (PeopleService peopleService){
        this.peopleService=peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person=(Person) target;

        if(peopleService.findByName(person.getName()).isPresent()){
            errors.rejectValue("name","","This name already is used");
        }
    }
}
