package com.stock.pstock.controller;

import com.stock.pstock.exception.RessourceNotfoundException;
import com.stock.pstock.model.Article;
import com.stock.pstock.model.Categorie;
import com.stock.pstock.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategorieController {
    @Autowired
    CategorieRepository categorieRepository;
    @GetMapping("/categories")
    public List<Categorie> getAllCategorie(){
        System.out.println("Get All Categorie ...");
        List<Categorie> categories=new ArrayList<>();
        categorieRepository.findAll().forEach(categories::add);
        return categories;
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable(value = "id") Long CategorieId)
            throws RessourceNotfoundException {

        Categorie categorie = categorieRepository.findById(CategorieId)
                .orElseThrow(() -> new RessourceNotfoundException("Categorie not Found : " +CategorieId));
        return ResponseEntity.ok().body(categorie);
    }
    @PostMapping("/addCategorie")
    public Categorie createCategorie(@Validated @RequestBody Categorie categorie){
        return  categorieRepository.save(categorie);
    }
    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteCategorie(@PathVariable(value = "id") Long CategorieId)
            throws RessourceNotfoundException{
        Categorie categorie =categorieRepository.findById(CategorieId)
                .orElseThrow(() -> new RessourceNotfoundException("Article not Found : " + CategorieId));
        categorieRepository.delete(categorie);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  response;
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") long id ,@RequestBody Categorie categorie){
        System.out.println("Delete Categorie with ID = " +id);
        Optional<Categorie> categorieInfo = categorieRepository.findById(id);
        if(categorieInfo.isPresent()){
            Categorie categorie1 =categorieInfo.get();
            categorie1.setCode(categorie.getCode());
            categorie1.setLibelle((categorie.getLibelle()));
            categorie1.setCode(categorie.getCode());
            categorie1.setLibelle(categorie.getLibelle());

            return  new ResponseEntity<>(categorieRepository.save(categorie), HttpStatus.OK);}
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
