package com.ghislain.boot.product.service;

import com.ghislain.boot.product.dto.ProduitDto;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    ProduitDto createProduit(ProduitDto produitDto);
    List<ProduitDto> getAllProduits();
    ProduitDto getProduitById(Long id);
    ProduitDto updateProduit(Long id, ProduitDto produitDto);
    void deleteProduit(Long id);
    List<ProduitDto> searchProduits(String query);
}
