package org.demo.controller;

import org.demo.model.Quotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //the anotation who telling as this is class controller
@RequestMapping("api/quotes")
public class ControllerQuotes {

    @GetMapping
    public List<Quotes> getAllQuotes() {
        return Quotes();
    }


}
