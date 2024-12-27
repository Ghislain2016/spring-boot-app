package com.ghislain.boot.product.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProduitDto {
    private Long id;

    @NotBlank(message = "Le nom du produit est obligatoire.")
    @Size(min = 2, max = 100, message = "Le nom doit comporter entre 2 et 100 caractères.")
    private String nom;

    @NotNull(message = "La description est obligatoire.")
    @Size(min = 10, max = 500, message = "La description doit comporter entre 10 et 500 caractères.")
    private String description;

    @NotNull(message = "Le prix est obligatoire.")
    @DecimalMin(value = "0.01", inclusive = true, message = "Le prix doit être supérieur ou égal à 0.01.")
    private Double prix;

    @NotNull(message = "La quantité est obligatoire.")
    @Min(value = 0, message = "La quantité ne peut pas être inférieure à 0.")
    private Integer quantite;

    @NotNull(message = "La date d'ajout est obligatoire.")
    private LocalDate dateAjout;

    @NotBlank(message = "La catégorie est obligatoire.")
    private String categorie;

    @NotBlank(message = "La référence est obligatoire.")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "La référence doit contenir uniquement des lettres, des chiffres et des tirets.")
    private String reference;
}
