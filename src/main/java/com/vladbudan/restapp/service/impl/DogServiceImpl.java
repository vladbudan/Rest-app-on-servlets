package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.exception.ResourceNotFoundException;
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
    public Optional<Dog> getDogById(int id) {

        Optional<Dog> optionalDog = dogRepository.getDogById(id);

        if (!optionalDog.isPresent()) {
            throw new ResourceNotFoundException("Dog with such id not found!");
        }

        return dogRepository.getDogById(id);

    }

    @Override
    public List<Dog> getAllDogs() {

        List<Dog> dogs = dogRepository.getAllDog();

        if (dogs == null) {
            throw new ResourceNotFoundException("Dogs with such id not found!");
        }

        return dogRepository.getAllDog();

    }

}
