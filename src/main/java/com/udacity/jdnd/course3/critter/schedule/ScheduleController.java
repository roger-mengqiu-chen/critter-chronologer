package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = DTOToEntity(scheduleDTO);
        schedule = scheduleService.saveSchedule(schedule);
        return entityToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return schedules.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        return  schedules.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        return schedules.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        return schedules.stream().map(e -> entityToDTO(e)).collect(Collectors.toList());
    }

    private ScheduleDTO entityToDTO (Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Employee> employees = schedule.getEmployees();
        List<Long> eIds = employees.stream().map(e -> e.getId()).collect(Collectors.toList());
        List<Pet> pets = schedule.getPets();
        List<Long> pIds = pets.stream().map(e -> e.getId()).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(eIds);
        scheduleDTO.setPetIds(pIds);
        return scheduleDTO;
    }

    private Schedule DTOToEntity (ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Long> eIds = scheduleDTO.getEmployeeIds();
        List<Employee> employees = eIds.stream().map(e -> employeeService.getEmployee(e)).collect(Collectors.toList());
        List<Long> pIds = scheduleDTO.getPetIds();
        List<Pet> pets = pIds.stream().map(e -> petService.getPetById(e)).collect(Collectors.toList());
        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }
}
