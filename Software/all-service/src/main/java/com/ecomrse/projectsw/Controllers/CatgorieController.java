package com.ecomrse.projectsw.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomrse.projectsw.Aspect.RequiredRole;
import com.ecomrse.projectsw.Models.Categorie;
import com.ecomrse.projectsw.Services.CategorieService;

@RestController
@RequestMapping("/categorie")
public class CatgorieController {
    @Autowired
    CategorieService categorieService;

    @CrossOrigin(origins = "*")
    @PostMapping("/manage/add")
    @RequiredRole("ADMIN")
    public ResponseEntity<String> add(@RequestBody Categorie categorie, @RequestParam("token") String token) {
        return categorieService.addCategorie(categorie);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/manage/{id}/update")
    @RequiredRole("ADMIN")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Categorie categorie,
            @RequestParam("token") String token) {
        categorie.setId(id);
        return categorieService.updateCategorie(categorie);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/manage/{id}/delete")
    @RequiredRole("ADMIN")
    public ResponseEntity<String> delete(@PathVariable long id, @RequestParam("token") String token) {
        return categorieService.deleteCategorie(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    @RequiredRole("ALL")
    public List<Categorie> get(@RequestParam("token") String token) {
        return categorieService.getCategories();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/count")
    @RequiredRole("ADMIN")
    public int count() {
        return categorieService.countUserUsers();
    }
}
