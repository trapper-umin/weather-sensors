package dev.rest.services;

import dev.rest.models.Sensor;
import dev.rest.repositories.SensorsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    public SensorsService(SensorsRepository sensorsRepository){
        this.sensorsRepository=sensorsRepository;
    }

    public List<Sensor> findAll(){
        return sensorsRepository.findAll();
    }

    public Sensor findById(int id){
        Optional<Sensor> sensor=sensorsRepository.findById(id);
        return sensor.orElse(null);
    }

    @Transactional
    public void registration(Sensor sensor){
        sensor.setCreatedAt(LocalDateTime.now());
        sensor.setUpdatedAt(LocalDateTime.now());

        sensorsRepository.save(sensor);
    }

    @Transactional
    public void update(int id,Sensor sensor){

        sensor.setId(id);
        sensor.setUpdatedAt(LocalDateTime.now());
        sensor.setCreatedAt(sensorsRepository.findById(id).get().getCreatedAt());

        sensorsRepository.save(sensor);
    }

    @Transactional
    public void delete(int id){
        sensorsRepository.deleteById(id);
    }

}
