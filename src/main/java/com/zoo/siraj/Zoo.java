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
    public void addToTreatmentEmployee(Employee employee,Animal animal){
        
    }
    public boolean removeFromTreatmentEmployee(Employee employee, Animal animal){

    }
    public boolean feedAnimal(Employee employee, Animal animal , Food food,int amount){

    }

}
