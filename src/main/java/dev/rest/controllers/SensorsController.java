package dev.rest.controllers;


import dev.rest.models.Sensor;
import dev.rest.services.SensorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;

    public SensorsController(SensorsService sensorsService){
        this.sensorsService=sensorsService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("sensors",sensorsService.findAll());

        return "sensors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("sensor",sensorsService.findById(id));

        return "sensors/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("sensor",sensorsService.findById(id));

        return "sensors/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("sensor") Sensor sensor){
        sensorsService.update(id,sensor);

        return "redirect:/sensors/{id}";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("sensor")Sensor sensor){
        return "sensors/registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("sensor") Sensor sensor){
        sensorsService.registration(sensor);

        return "redirect:/sensors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        sensorsService.delete(id);

        return "redirect:/sensors";
    }

}
