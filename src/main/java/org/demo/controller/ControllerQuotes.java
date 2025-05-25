package org.demo.controller;


import org.demo.model.Quotes;
import org.demo.repository.RepositoryQuotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ControllerQuotes {

    @Autowired
    private RepositoryQuotes repositoryQuotes;

    @GetMapping
    public List<Quotes> getAllQuotes (){

        return repositoryQuotes.findAll();

    }

    @PostMapping
    public Quotes crateQuotes(@RequestBody Quotes quotes) {
        return repositoryQuotes.save(quotes);
    }

}
