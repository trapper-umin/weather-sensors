package dev.rest.services;

import dev.rest.models.Person;
import dev.rest.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService (PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    public Optional<Person> findByName(String name){
        return peopleRepository.findByName(name);
    }

    @Transactional
    public void registration(Person person){

        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setRole("ROLE_USER");

        peopleRepository.save(person);
    }
}
