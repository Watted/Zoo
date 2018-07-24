package com.zoo.siraj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo {
  private List<Cage> cages;
  private List<Employee> employees;
  private Map<Food,Integer> foods;

    public Zoo() {
        cages = new ArrayList<>();
        employees = new ArrayList<>();
        foods = new HashMap<>();
    }

    public boolean removeAnimalFromCage(Animal animal, Cage cage) {
        return cage.removeAnimal(animal);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

}
