package com.vladbudan.restapp.repository;

import com.vladbudan.restapp.model.Pet;

import java.util.List;

public interface PetRepository {

    Pet save(Pet pet);
    void update(Pet pet);
    Pet delete(Integer id );
    Pet getById(Integer id);
    List<Pet> getAll();
}
