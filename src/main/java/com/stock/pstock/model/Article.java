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
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String libelle;
    private Float prix_achat;
    private  int tva;
    private int fodec;
    private Float pv;
    private Float stock_init;
    private float stock;
    private String image;
    private Long id_cat;
    private  Long id_scat;

    public Article(String code, String libelle, Float prix_achat, int tva, int fodec, Float pv, Float stock_init, float stock, String image, Long id_cat, Long id_scat) {
        this.code = code;
        this.libelle = libelle;
        this.prix_achat = prix_achat;
        this.tva = tva;
        this.fodec = fodec;
        this.pv = pv;
        this.stock_init = stock_init;
        this.stock = stock;
        this.image = image;
        this.id_cat = id_cat;
        this.id_scat = id_scat;

}
}
