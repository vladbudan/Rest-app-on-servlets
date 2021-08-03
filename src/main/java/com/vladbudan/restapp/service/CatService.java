package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {

    Optional<Cat> getCatById(int id);

    Cat addCat(Cat cat);

    Cat update(Cat cat);

    void deleteCat(int id);

    List<Cat> getAllCats();
}
