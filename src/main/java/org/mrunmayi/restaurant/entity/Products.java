package org.mrunmayi.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="product_name", nullable=false)
    private String name;

    @Column(name="price", nullable = false)
    private double price;
}
