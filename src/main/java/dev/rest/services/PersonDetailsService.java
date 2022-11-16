package dev.rest.services;

import dev.rest.models.Person;
import dev.rest.repositories.PeopleRepository;
import dev.rest.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person=peopleRepository.findByName(username);

        if(person.isEmpty())
            throw new UsernameNotFoundException("User with this username wasn't found");

        return new PersonDetails(person.get());
    }
}
