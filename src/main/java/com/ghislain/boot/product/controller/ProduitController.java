package com.ghislain.boot.product.controller;
import com.ghislain.boot.product.dto.ProduitDto;
import com.ghislain.boot.product.exceptions.ProduitNotFoundException;
import com.ghislain.boot.product.service.ProduitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produits")
public class ProduitController {
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitDto> createProduit(@Valid @RequestBody ProduitDto produitDto) {
        // Vous pouvez créer un DTO à partir du produitValidator et l'envoyer à la couche service
        ProduitDto createdProduit = produitService.createProduit(produitDto);
        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAllProduits() {
        List<ProduitDto> produits = produitService.getAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> getProduitById(@PathVariable Long id) {
        try {
            ProduitDto produit = produitService.getProduitById(id);
            return new ResponseEntity<>(produit, HttpStatus.OK);
        } catch (ProduitNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> updateProduit(@PathVariable Long id, @Valid @RequestBody ProduitDto produitDto) {
        try {
            ProduitDto updatedProduit = produitService.updateProduit(id, produitDto);
            return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
        } catch (ProduitNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        try {
            produitService.deleteProduit(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProduitNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProduitDto>> searchProduits(@RequestParam String query) {
        List<ProduitDto> produits = produitService.searchProduits(query);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }
}
