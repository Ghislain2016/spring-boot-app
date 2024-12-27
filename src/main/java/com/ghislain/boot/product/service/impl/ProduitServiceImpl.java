package com.ghislain.boot.product.service.impl;

import com.ghislain.boot.product.dto.ProduitDto;
import com.ghislain.boot.product.entity.Produit;
import com.ghislain.boot.product.exceptions.ProduitNotFoundException;
import com.ghislain.boot.product.mapper.ProduitMapper;
import com.ghislain.boot.product.repository.ProduitRepository;
import com.ghislain.boot.product.service.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepository;
    private ProduitMapper produitMapper;

    @Override
    public ProduitDto createProduit(ProduitDto produitDto) {
        Produit produit = produitMapper.toEntity(produitDto);
        produit = produitRepository.save(produit);
        return produitMapper.toDto(produit);
    }

    @Override
    public List<ProduitDto> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return produits.stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProduitDto getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit non trouvé avec l'ID " + id));
        return produitMapper.toDto(produit);
    }

    @Override
    public ProduitDto updateProduit(Long id, ProduitDto produitDto) {
        if (!produitRepository.existsById(id)) {
            throw new ProduitNotFoundException("Produit avec l'ID " + id + " n'existe pas.");
        }
        Produit produit = produitMapper.toEntity(produitDto);
        produit.setId(id);
        produit = produitRepository.save(produit);
        return produitMapper.toDto(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new ProduitNotFoundException("Produit avec l'ID " + id + " n'existe pas.");
        }
        produitRepository.deleteById(id);
    }

    @Override
    public List<ProduitDto> searchProduits(String query) {
        List<Produit> produits = produitRepository.findByNomContainingIgnoreCaseOrCategorieContainingIgnoreCase(query, query);
        if (produits.isEmpty()) {
            throw new ProduitNotFoundException("Aucun produit trouvé pour la requête : " + query);
        }
        return produits.stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }
}
