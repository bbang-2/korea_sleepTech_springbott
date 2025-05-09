package com.example.korea_sleepTech_springboot.시험.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column (nullable = true, length = 1000)
    private String description;

    private BigDecimal price;

    @CreatedDate
    private long createdAt;

    @UpdateTimestamp
    private long updatedAt;

    public Product(Long o, String name, String description, BigDecimal price, Object o1, Object o2) {
    }

    @PrePersist
    public void prePersist() {
        long currentTimeMillis = System.currentTimeMillis();
        this.createdAt = currentTimeMillis;
        this.updatedAt = currentTimeMillis;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}
