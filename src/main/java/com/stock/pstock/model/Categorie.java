package com.stock.pstock.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String libelle;

    public Categorie(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
}
