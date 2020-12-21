package com.stock.pstock.controller;

import com.stock.pstock.exception.RessourceNotfoundException;
import com.stock.pstock.model.Client;
import com.stock.pstock.model.Fournisseur;
import com.stock.pstock.repository.ClientRepository;
import com.stock.pstock.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FournisseurController {
    @Autowired
    FournisseurRepository fournisseurRepository;
    @GetMapping("/fournisseurs")
    public List<Fournisseur> getAllFournisseur(){
        System.out.println("Get All Fournisseur ...");
        List<Fournisseur> fournisseurs=new ArrayList<>();
        fournisseurRepository.findAll().forEach(fournisseurs::add);
        return fournisseurs;
    }
    @GetMapping("/fournisseurs/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") Long FournisseurId)
            throws RessourceNotfoundException {

        Fournisseur fournisseur = fournisseurRepository.findById(FournisseurId)
                .orElseThrow(() -> new RessourceNotfoundException("Fournisseur not Found : " +FournisseurId));
        return ResponseEntity.ok().body(fournisseur);
    }
    @PostMapping("/addFournisseur")
    public Fournisseur createFournisseur(@Validated @RequestBody Fournisseur fournisseur){
        return  fournisseurRepository.save(fournisseur);
    }
    @DeleteMapping("/fournisseurs/{id}")
    public Map<String, Boolean> deleteFournisseur(@PathVariable(value = "id") Long FournisseurId)
            throws RessourceNotfoundException{
        Fournisseur fournisseur =fournisseurRepository.findById(FournisseurId)
                .orElseThrow(() -> new RessourceNotfoundException("Fournisseur not Found : " + FournisseurId));
        fournisseurRepository.delete(fournisseur);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  response;
    }
    @PutMapping("/fournisseurs/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable("id") long id ,@RequestBody Fournisseur fournisseur){
        System.out.println("Delete Fournisseur with ID = " +id);
        Optional<Fournisseur> FournisseurInfo = fournisseurRepository.findById(id);
        if(FournisseurInfo.isPresent()){
            Fournisseur fournisseur1 =FournisseurInfo.get();
            fournisseur1.setCode(fournisseur.getCode());
            fournisseur1.setLibelle((fournisseur.getLibelle()));
            fournisseur1.setAdresse(fournisseur.getAdresse());
            fournisseur1.setTel(fournisseur.getTel());
            fournisseur1.setEmail(fournisseur.getEmail());
            fournisseur1.setMatFisc(fournisseur.getMatFisc());
            fournisseur1.setAsuj(fournisseur.getAsuj());
            fournisseur1.setTimbre(fournisseur.getTimbre());
            fournisseur1.setSolde(fournisseur.getSolde());
            fournisseur1.setSoldeInit(fournisseur.getSoldeInit());
            fournisseur1.setLogin(fournisseur.getLogin());
            fournisseur1.setPassword(fournisseur.getPassword());
            return  new ResponseEntity<>(fournisseurRepository.save(fournisseur), HttpStatus.OK);}
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
