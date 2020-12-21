package com.stock.pstock.controller;

import com.stock.pstock.exception.RessourceNotfoundException;
import com.stock.pstock.model.Client;
import com.stock.pstock.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @GetMapping("/clients")
    public List<Client> getAllClient(){
        System.out.println("Get All Client ...");
        List<Client> clients=new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long ClientId)
            throws RessourceNotfoundException {

        Client client = clientRepository.findById(ClientId)
                .orElseThrow(() -> new RessourceNotfoundException("Client not Found : " +ClientId));
        return ResponseEntity.ok().body(client);
    }
    @PostMapping("/addClient")
    public Client createClient(@Validated @RequestBody Client client){
        return  clientRepository.save(client);
    }
    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long ClientId)
            throws RessourceNotfoundException{
        Client client =clientRepository.findById(ClientId)
                .orElseThrow(() -> new RessourceNotfoundException("Client not Found : " + ClientId));
        clientRepository.delete(client);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  response;
    }
    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id ,@RequestBody Client client){
        System.out.println("Delete Client with ID = " +id);
        Optional<Client> ClientInfo = clientRepository.findById(id);
        if(ClientInfo.isPresent()){
            Client client1 =ClientInfo.get();
            client1.setLibelle((client.getLibelle()));
            client1.setAdresse(client.getAdresse());
            client1.setTel(client.getTel());
            client1.setEmail(client.getEmail());
            client1.setFax(client.getFax());
            client1.setLogin(client.getLogin());
            client1.setPassword(client.getPassword());
            return  new ResponseEntity<>(clientRepository.save(client), HttpStatus.OK);}
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
