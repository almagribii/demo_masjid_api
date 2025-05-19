package org.demo.controller;


import org.demo.model.Kegiatan;
import org.demo.payload.KegiatanPayload;
import org.demo.repository.RepositoryKegiatan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/kegiatan")
public class ControllerKegiatan {

    @Autowired
    private RepositoryKegiatan repositoryKegiatan;

    @GetMapping
    public List<Kegiatan> getAllKegiatan() {
        return repositoryKegiatan.findAll();
    }

    @PostMapping
    public Kegiatan createKegiatan(@RequestBody KegiatanPayload kegiatanPayload) {
        var date = LocalDateTime.now();
        var kegiatan = new Kegiatan();
        kegiatan.setTitle(kegiatanPayload.getTitle());
        kegiatan.setDescription(kegiatanPayload.getDescription());
        kegiatan.setDate(date);
        return repositoryKegiatan.save(kegiatan);
    }
    @GetMapping("/{id}")
    public Kegiatan getKegiatanById(@PathVariable Long id) {
        return repositoryKegiatan.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Kegiatan updateKegiatan(@PathVariable Long id, @RequestBody Kegiatan updated) {
        return repositoryKegiatan.findById(id).map(kegiatan -> {
            kegiatan.setTitle(updated.getTitle());
            kegiatan.setDescription(updated.getDescription());
            kegiatan.setDate(updated.getDate());
            return repositoryKegiatan.save(kegiatan);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletedKegiatan(@PathVariable Long id) {
        repositoryKegiatan.deleteById(id);
    }

}
