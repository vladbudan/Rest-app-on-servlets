package com.vladbudan.restapp.model;

import lombok.Data;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue(value = "dog_type")
public class Dog extends Pet{
    private String color;
}
