package org.demo.repository;

import org.demo.model.KegiatanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryKegiatan extends JpaRepository<KegiatanModel,Long> {
}
