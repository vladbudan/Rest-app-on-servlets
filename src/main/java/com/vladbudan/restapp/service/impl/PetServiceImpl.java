package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.model.Pet;
import com.vladbudan.restapp.repository.CatRepository;
import com.vladbudan.restapp.repository.DogRepository;
import com.vladbudan.restapp.repository.PetRepository;
import com.vladbudan.restapp.service.PetService;

import java.util.List;

public class PetServiceImpl implements PetService {

    private final DogRepository dogRepository;
    private final CatRepository catRepository;
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository, CatRepository catRepository, DogRepository dogRepository) {
        this.petRepository = petRepository;
        this.catRepository = catRepository;
        this.dogRepository = dogRepository;
    }

    @Override
    public Pet getById(int id) {
        return petRepository.getById(id);
    }

    @Override
    public Cat getByIdCat(int id) {
        return catRepository.getById(id);
    }

    @Override
    public Dog getByIdDog(int id) {
        return dogRepository.getById(id);
    }

    @Override
    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public void delete(int id) {
        petRepository.delete(id);
    }

    @Override
    public void deleteCat(int id) {
        catRepository.delete(id);
    }

    @Override
    public void deleteDog(int id) {
        dogRepository.delete(id);
    }

    @Override
    public void update(Pet pet) {
        petRepository.update(pet);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.getAllCat();
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogRepository.getAllDog();
    }

    @Override
    public List<Pet> getAllByUserId() {
        return petRepository.getAll();
    }

}
