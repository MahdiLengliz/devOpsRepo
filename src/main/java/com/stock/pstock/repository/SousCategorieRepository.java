package com.stock.pstock.repository;

import com.stock.pstock.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie,Long> {
}
