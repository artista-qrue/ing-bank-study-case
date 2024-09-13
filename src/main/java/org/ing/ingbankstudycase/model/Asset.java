package org.ing.ingbankstudycase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:17
 */

@Entity
@Table(name = "asset")
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String assetName;
    private Double size;
    private Double usableSize;
}
