package org.demo.repository;

import org.demo.model.Kegiatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryKegiatan extends JpaRepository<Kegiatan,Long> {
}
