package org.demo.service;

import org.demo.repository.RepositoryKegiatan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceKegiatan {

    @Autowired
    private RepositoryKegiatan repositoryKegiatan;


}
