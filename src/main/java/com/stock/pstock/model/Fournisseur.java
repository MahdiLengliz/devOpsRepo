package com.stock.pstock.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@NoArgsConstructor
@Data
@ToString
@Table (name = "fournisseur")
public class Fournisseur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String libelle;
    private  String adresse;
    private  int tel;
    private  String email;
    private String matFisc;
    private  String asuj;
    private  String timbre;
    private Float solde;
    private Float soldeInit;
    private  String login;
    private  String password;

    public Fournisseur(String code, String libelle, String adresse, int tel, String email, String matFisc, String asuj, String timbre, Float solde, Float soldeInit, String login, String password) {
        this.code = code;
        this.libelle = libelle;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.matFisc = matFisc;
        this.asuj = asuj;
        this.timbre = timbre;
        this.solde = solde;
        this.soldeInit = soldeInit;
        this.login = login;
        this.password = password;
    }
}
