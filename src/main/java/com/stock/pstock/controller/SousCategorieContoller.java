package com.stock.pstock.controller;

import com.stock.pstock.exception.RessourceNotfoundException;
import com.stock.pstock.model.Categorie;
import com.stock.pstock.model.SousCategorie;
import com.stock.pstock.repository.CategorieRepository;
import com.stock.pstock.repository.SousCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SousCategorieContoller {
    @Autowired
    SousCategorieRepository sousCategorieRepository;
    @GetMapping("/sousCategories")
    public List<SousCategorie> getAllSousCategorie(){
        System.out.println("Get All SousCategories ...");
        List<SousCategorie> sousCategories=new ArrayList<>();
        sousCategorieRepository.findAll().forEach(sousCategories::add);
        return sousCategories;
    }
    @GetMapping("/sousCategories/{id}")
    public ResponseEntity<SousCategorie> getSousCategorieById(@PathVariable(value = "id") Long SousCategorieId)
            throws RessourceNotfoundException {

        SousCategorie sousCategorie = sousCategorieRepository.findById(SousCategorieId)
                .orElseThrow(() -> new RessourceNotfoundException("Categorie not Found : " +SousCategorieId));
        return ResponseEntity.ok().body(sousCategorie);
    }
    @PostMapping("/addSousCategorie")
    public SousCategorie createSousCategorie(@Validated @RequestBody SousCategorie sousCategorie){
        return  sousCategorieRepository.save(sousCategorie);
    }
    @DeleteMapping("/sousCategories/{id}")
    public Map<String, Boolean> deleteSousCategorie(@PathVariable(value = "id") Long SousCategorieId)
            throws RessourceNotfoundException{
        SousCategorie sousCategorie =sousCategorieRepository.findById(SousCategorieId)
                .orElseThrow(() -> new RessourceNotfoundException("Article not Found : " + SousCategorieId));
        sousCategorieRepository.delete(sousCategorie);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  response;
    }
    @PutMapping("/sousCategories/{id}")
    public ResponseEntity<SousCategorie> updateSousCategorie(@PathVariable("id") long id ,@RequestBody SousCategorie sousCategorie){
        System.out.println("Delete Categorie with ID = " +id);
        Optional<SousCategorie> sousCategorieInfo = sousCategorieRepository.findById(id);
        if(sousCategorieInfo.isPresent()){
            SousCategorie sousCategorie1 =sousCategorieInfo.get();
            sousCategorie1.setCode(sousCategorie.getCode());
            sousCategorie1.setLibelle((sousCategorie.getLibelle()));
            sousCategorie1.setCode_categ(sousCategorie.getCode_categ());
            sousCategorie1.setId_cat(sousCategorie.getId_cat());

            return  new ResponseEntity<>(sousCategorieRepository.save(sousCategorie), HttpStatus.OK);}
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
