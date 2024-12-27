package com.ghislain.boot.product.repository;

import com.ghislain.boot.product.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContainingIgnoreCaseOrCategorieContainingIgnoreCase(String nom, String categorie);
}
