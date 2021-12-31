package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long id) {
        Pet pet = petRepository.getOne(id);
        return scheduleRepository.findScheduleByPets(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long id) {
        Employee employee = employeeRepository.getOne(id);
        return scheduleRepository.findScheduleByEmployees(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long id) {
        Customer customer = customerRepository.getOne(id);
        List<Pet> pets = petRepository.findPetsByCustomer(customer);
        return scheduleRepository.findScheduleByPetsIn(pets);
    }

}
