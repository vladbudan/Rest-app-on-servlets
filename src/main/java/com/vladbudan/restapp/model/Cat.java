package com.vladbudan.restapp.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(value = "cat_type")
public class Cat extends Pet{
    private String gender;

}
