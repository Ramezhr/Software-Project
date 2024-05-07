package com.ecomrse.projectsw.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomrse.projectsw.Models.Categorie;

@Repository
public interface CategorieRepo  extends JpaRepository<Categorie,Long> {
    Optional<Categorie> findByName(String name);
}
