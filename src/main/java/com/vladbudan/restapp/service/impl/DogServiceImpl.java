package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.repository.DogRepository;
import com.vladbudan.restapp.service.DogService;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    public Optional<Dog> getDogById(int id) {
        return dogRepository.getDogById(id);
    }

    @Override
    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog update(Dog dog) {

        return dogRepository.update(dog);
    }

    @Override
    public void deleteDog(int id) {
        dogRepository.delete(id);
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogRepository.getAllDog();
    }

}
