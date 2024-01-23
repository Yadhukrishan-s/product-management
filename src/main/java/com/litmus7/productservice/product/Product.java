package com.litmus7.productservice.product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, message = "Invalid Name: Atleast 4 charaters is needed")
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String name;


    @NotNull(message = "Invalid Price: Price is NULL")
    @DecimalMin(value = "1.0", inclusive = true, message = "Atleast 1.0 $ is necessary")
    @DecimalMax(value = "1000000.9", inclusive = true, message = "Maximum value can only be 1000000$")
    private double price;
    
}
