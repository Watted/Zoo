package com.zoo.siraj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private static int idOfEmployee = 0;
    private String id;
    private String name;
    private List<Animal> treatmentAnimals;

    public Employee(String name) {
        this.id = String.valueOf(++idOfEmployee);
        this.name = name;
        this.treatmentAnimals = new ArrayList<>();
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
        if (treatmentAnimals.contains(animal)) {
            int index = this.treatmentAnimals.indexOf(animal);
            Animal animalToFeed = this.treatmentAnimals.get(index);
            animalToFeed.addFoodToAnimal(amount, food);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", treatmentAnimals=" + treatmentAnimals +
                '}';
    }
}
