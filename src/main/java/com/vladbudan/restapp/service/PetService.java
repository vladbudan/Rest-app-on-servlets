package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.model.Pet;
import com.vladbudan.restapp.model.User;

import java.util.List;

public interface PetService {

    Pet getById(int id);
    Cat getByIdCat(int id);
    Dog getByIdDog(int id);
    void addCat(Cat cat);
    Dog addDog(Dog dog);
    void delete(int id);
    void deleteCat(int id);
    void deleteDog(int id);
    void update(Pet pet);
    List<Pet> getAllByUserId();
    List<Cat> getAllCats();
    List<Dog> getAllDogs();
}
