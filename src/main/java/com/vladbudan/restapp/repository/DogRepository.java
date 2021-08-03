package com.vladbudan.restapp.repository;

import com.vladbudan.restapp.model.Dog;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    Dog save(Dog dog);

    Dog update(Dog dog);

    void delete(Integer id);

    Optional<Dog> getDogById(Integer id);

    List<Dog> getAllDog();
}
