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
@Table(name = "SousCategorie")
public class SousCategorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private  String code_categ;
    private String libelle;
    private Long id_cat;

    public SousCategorie(String code, String code_categ, String libelle, Long id_cat) {
        this.code = code;
        this.code_categ = code_categ;
        this.libelle = libelle;
        this.id_cat = id_cat;
    }
}
