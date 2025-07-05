package org.demo.repository;

import org.demo.model.MasjidBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RepositorySaldo extends JpaRepository <MasjidBalance,Long> {
        Optional<MasjidBalance> findByDate(LocalDate date);
}

