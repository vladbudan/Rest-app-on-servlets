package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.Dog;

import java.util.List;
import java.util.Optional;


public interface DogService {

    Optional<Dog> getDogById(int id);

    Dog addDog(Dog dog);

    Dog update(Dog dog);

    void deleteDog(int id);

    List<Dog> getAllDogs();
}
