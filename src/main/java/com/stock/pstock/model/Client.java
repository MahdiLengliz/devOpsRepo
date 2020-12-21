package com.stock.pstock.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Data
@ToString
@NoArgsConstructor
@Entity
@Table (name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String libelle;
    private  String adresse;
    private  int tel;
    private  String email;
    private  String fax;
    private  String login;
    private  String password;

    public Client(long id, String libelle, String adresse, int tel, String email, String fax, String login, String password) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.fax = fax;
        this.login = login;
        this.password = password;
    }
}
