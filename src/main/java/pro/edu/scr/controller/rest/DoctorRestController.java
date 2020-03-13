package pro.edu.scr.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.edu.scr.model.Doctor;
import pro.edu.scr.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRestController {
    private List<Doctor> doctors = new ArrayList<>(Arrays.asList(

            new Doctor("1","First",23),
            new Doctor("2","Second",132)
    ));



    @RequestMapping("/get/list")
    public List<Doctor> showAll(){
        return doctors;
    }




    @RequestMapping("/get/{id}")
    Doctor show(@PathVariable("id") String id){
        return doctors.stream().filter(doctor -> doctor.getId().equals(id)).findFirst().orElse(null);
    }



    @RequestMapping("/delete/{id}")
    Doctor delete(@PathVariable("id") String id){
        Doctor doc = doctors.stream().filter(doctor -> doctor.getId().equals(id)).findFirst().orElse(null);
        doctors.remove(id);
        return doc;
    }

}
