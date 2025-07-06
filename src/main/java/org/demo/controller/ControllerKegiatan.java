package org.demo.controller;


import org.demo.model.Kegiatan;
import org.demo.repository.RepositoryKegiatan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kegiatan")
public class ControllerKegiatan {

    @Autowired
    private RepositoryKegiatan repositoryKegiatan;

//    this the function get all quotes
    @GetMapping
    public List<Kegiatan> getAllQuotes(){
        return repositoryKegiatan.findAll();
    }

    @PostMapping
    public Kegiatan createKegiatan(@RequestBody Kegiatan kegiatan){
        return repositoryKegiatan.save(kegiatan);
    }
}
