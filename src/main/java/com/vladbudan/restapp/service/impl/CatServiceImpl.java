package com.vladbudan.restapp.service.impl;

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
    public Optional<Cat> getCatById(int id) {
        return catRepository.getCatById(id);
    }

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
    public List<Cat> getAllCats() {
        return catRepository.getAllCat();
    }

}
