package org.ing.ingbankstudycase.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:07
 */
@Data
@Entity
@Table(name = "_order")
public class Order {

    public enum Side {
        BUY, SELL
    }

    public enum Status {
        PENDING, MATCHED, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String assetName;
    @Enumerated(EnumType.STRING)
    private Side orderSide;
    private Double size;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createDate = LocalDateTime.now();
}
