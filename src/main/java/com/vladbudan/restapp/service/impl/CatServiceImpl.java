package com.vladbudan.restapp.service.impl;

import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.repository.CatRepository;
import com.vladbudan.restapp.service.CatService;

import java.util.List;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public void save(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public void update(Cat cat) {
        catRepository.update(cat);
    }

    @Override
    public void delete(Integer id) {
        catRepository.delete(id);
    }

    @Override
    public Cat getById(Integer id) {
        return catRepository.getById(id);
    }
}
