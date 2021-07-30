package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.exception.ResourceNotFoundException;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.repository.CatRepository;
import com.vladbudan.restapp.service.CatService;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public Cat addCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat update(Cat cat) {

        return catRepository.update(cat);
    }

    @Override
    public void deleteCat(int id) {
        catRepository.delete(id);
    }

    @Override
    public Optional<Cat> getCatById(int id) {

        Optional<Cat> optionalCat = catRepository.getCatById(id);

        if (!optionalCat.isPresent()) {
            throw new ResourceNotFoundException("Cat with such id not found!");
        }

        return catRepository.getCatById(id);

    }

    @Override
    public List<Cat> getAllCats() {

        List<Cat> cats = catRepository.getAllCat();

        if (cats == null) {
            throw new ResourceNotFoundException("Cats failed!");
        }

        return catRepository.getAllCat();

    }

}
