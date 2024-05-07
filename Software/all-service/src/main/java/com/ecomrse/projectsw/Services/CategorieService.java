package com.ecomrse.projectsw.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomrse.projectsw.Models.Categorie;
import com.ecomrse.projectsw.Repository.CategorieRepo;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepo categorieRepo;

    public List<Categorie> getCategories() {
        List<Categorie> result = new ArrayList<Categorie>();
        categorieRepo.findAll().forEach(result::add);
        return result;
    }

    // Add Categorie

    public ResponseEntity<String> addCategorie(Categorie categorie) {
        // for validation
        if (categorie.getName() == null || categorie.getName() == "") {
            return ResponseEntity.badRequest().body("Name of the Categorie cannot be empty");
        }
        if (categorie.getDescription() == null || categorie.getDescription() == "") {
            return ResponseEntity.badRequest().body("Description of the Categorie cannot be empty");
        }
        if (categorieRepo.findByName(categorie.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Name of the Categorie Already Exist");
        }

        categorieRepo.save(categorie);
        return ResponseEntity.ok("Categorie saved successfully");
    }

    // update Categorie
    public ResponseEntity<String> updateCategorie(Categorie categorie) {
        // for validation
        if (categorie.getName() == null || categorie.getName() == "") {
            return ResponseEntity.badRequest().body("Name of the Categorie cannot by Empty");
        }
        if (categorie.getDescription() == null || categorie.getDescription() == "") {
            return ResponseEntity.badRequest().body("Description of the Categorie cannot by Empty");
        }
        categorieRepo.save(categorie);
        return ResponseEntity.ok("Categorie Updated successfully");
    }

    public ResponseEntity<String> deleteCategorie(long id) {
        categorieRepo.deleteById(id);
        return ResponseEntity.ok("Categorie Deleted successfully");
    }

    public int countUserUsers() {
        return (int) categorieRepo.count();
    }

}
