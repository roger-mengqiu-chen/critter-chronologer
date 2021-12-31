package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner (Customer customer) {
        return petRepository.findPetsByCustomer(customer);
    }

    public List<Long> getIdsByOwner (Customer customer) {
        return petRepository.getIdsByOwner(customer);
    }
}
