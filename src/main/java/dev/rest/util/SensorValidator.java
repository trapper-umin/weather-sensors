package dev.rest.util;

import dev.rest.models.Sensor;
import dev.rest.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService){
        this.sensorsService=sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor=(Sensor)target;
        if(sensorsService.findByName(sensor).isPresent()){
            errors.rejectValue("name","","Name should be unique");
        }
    }
}
