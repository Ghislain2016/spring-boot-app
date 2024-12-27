package com.ghislain.boot.product.mapper;

import com.ghislain.boot.product.dto.ProduitDto;
import com.ghislain.boot.product.entity.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {

    public ProduitDto toDto(Produit produit) {
        return ProduitDto.builder()
                .id(produit.getId())
                .nom(produit.getNom())
                .description(produit.getDescription())
                .prix(produit.getPrix())
                .quantite(produit.getQuantite())
                .categorie(produit.getCategorie())
                .reference(produit.getReference())
                .build();
    }

    public Produit toEntity(ProduitDto produitDto) {
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setNom(produitDto.getNom());
        produit.setDescription(produitDto.getDescription());
        produit.setPrix(produitDto.getPrix());
        produit.setQuantite(produitDto.getQuantite());
        produit.setCategorie(produitDto.getCategorie());
        produit.setReference(produitDto.getReference());
        return produit;
    }

}
