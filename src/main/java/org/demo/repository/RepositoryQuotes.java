package org.demo.repository;

import org.demo.model.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryQuotes extends JpaRepository <Quotes,Long> {

}

