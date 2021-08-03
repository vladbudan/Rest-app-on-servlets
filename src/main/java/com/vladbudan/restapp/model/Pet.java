package com.vladbudan.restapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static javax.persistence.DiscriminatorType.STRING;

@Data
@Entity
@Table(name = "pets")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "PET_TYPE", discriminatorType = STRING)
public class Pet {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "pet_id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}