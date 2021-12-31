package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequest;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        employeeRepository.getOne(id).setDaysAvailable(daysAvailable);
    }

    public List<Employee> findEmployeesForService(EmployeeRequest employeeRequest) {
        DayOfWeek day = employeeRequest.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeRequest.getSkills();
        List<Employee> employees = employeeRepository.findEmployeesByDaysAvailableAndSkillsIn(day, skills);
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSkills().containsAll(skills)) {
                result.add(e);
            }
        }
        return result;
    }
}
