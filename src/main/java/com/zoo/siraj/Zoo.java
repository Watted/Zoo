package com.zoo.siraj;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo implements Serializable {
    private List<Cage> cages;
    private List<Employee> employees;
    private Map<Food, Integer> foods;

    public Zoo() {
        cages = new ArrayList<>();
        employees = new ArrayList<>();
        foods = new HashMap<>();
    }

    public void addCage(Cage cage) {
        cages.add(cage);
    }

    public boolean removeCage(Cage cage) {
        return cages.remove(cage);
    }

    public void addAnimalToCage(Animal animal, Cage cage) {
        cage.addAnimal(animal);
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

    public void addFood(Food food) {
        this.foods.put(food, 0);
    }

    public boolean removeFood(Food food) {
        if (this.foods.containsKey(food)) {
            this.foods.remove(food);
            return true;
        }
        return false;
    }

    public boolean buyFood(Food food, int amountToBuy) {
        int sumOfAllMissingAmount = 0;
        for (Cage cage : cages) {
            for (Animal animal : cage.getContentAnimal()) {
                if (animal.getExistingFood().containsKey(food)) {
                    sumOfAllMissingAmount += animal.getMaxFoodPerKind(food) - animal.getExistingFood().get(food);
                }
            }
        }
        if (amountToBuy <= sumOfAllMissingAmount) {
            int amountBeforeBuying = this.foods.get(food);
            this.foods.put(food, amountToBuy + amountBeforeBuying);
            System.out.println("The food was updated with the new amount!");
            return true;
        }
        System.out.println("The food was not bought!!");
        return false;
    }


    public void addToTreatmentEmployee(Employee employee, Animal animal) {
        employee.addAnimal(animal);
    }

    public boolean removeFromTreatmentEmployee(Employee employee, Animal animal) {
        return employee.removeAnimal(animal);
    }

    public boolean feedAnimal(Employee employee, Animal animal, Food food, int amount) {
        int allowedToEat = animal.getMaxFoodPerKind(food) - animal.getExistingFoodPerKind(food) - animal.getEatenFoodPerKind(food);
        if (amount <= allowedToEat) {
            return employee.feedAnimals(animal, food, amount);
        }
        else {
            return false;
        }
    }
}
