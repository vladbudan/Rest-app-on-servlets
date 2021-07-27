package com.vladbudan.restapp.repository;

import com.vladbudan.restapp.model.Dog;

import java.util.List;

public interface DogRepository {

    Dog save(Dog dog);
    void update(Dog dog);
    void delete(Integer id );
    Dog getById(Integer id);
    List<Dog> getAllDog();
}
