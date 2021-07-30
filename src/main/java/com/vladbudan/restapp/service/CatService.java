package com.vladbudan.restapp.service;

import com.vladbudan.restapp.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {

    Cat addCat(Cat cat);

    Cat update(Cat cat);

    void deleteCat(int id);

    Optional<Cat> getCatById(int id);

    List<Cat> getAllCats();
}
