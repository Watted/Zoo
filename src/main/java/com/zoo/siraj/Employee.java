package com.zoo.siraj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employee {
    private static int idOfEmployee = 0;
    private String id;
    private String name;
    private List<Animal> treatmentAnimals;

    public Employee(String name) {
        this.id = String.valueOf(++idOfEmployee);
        this.name = name;

        this.treatmentAnimals = new ArrayList<>();
        System.out.println("sadsad");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getTreatmentAnimals() {
        return treatmentAnimals;
    }

    public void setTreatmentAnimals(List<Animal> treatmentAnimals) {
        this.treatmentAnimals = treatmentAnimals;
    }

    public void addAnimal(Animal animal){
        this.treatmentAnimals.add(animal);
    }

    public boolean removeAnimal(Animal animal){
        return this.treatmentAnimals.remove(animal);
    }

    public boolean feedAnimals(Animal animal ,Food food, int amount){
        int index = this.treatmentAnimals.indexOf(animal);
        if (index !=-1) {
            Animal animal1 = this.treatmentAnimals.get(index);
            animal1.updateFoodAmount(amount, food);
            return true;
        }
        return false;
    }
}
