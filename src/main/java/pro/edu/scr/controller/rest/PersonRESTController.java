package pro.edu.scr.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.edu.scr.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonRESTController {

    private List<Person> persons = new ArrayList<>(Arrays.asList(

            new Person("1","First",23),
            new Person("2","Second",23)
    ));


    @RequestMapping("/list")
    public List<Person> showAll(){
        return persons;
    }



}
