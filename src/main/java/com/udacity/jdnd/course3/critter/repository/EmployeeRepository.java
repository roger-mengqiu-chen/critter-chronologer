package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeesByDaysAvailableAndSkillsIn(DayOfWeek day, Set<EmployeeSkill> skills);
}
