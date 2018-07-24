package com.zoo.siraj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zoo {
    private List<Cage> cages;
    private List<Employee> employees;
    private Map<Food, Integer> foods;

    public Zoo() {
        cages = new ArrayList<>();
        employees = new ArrayList<>();
        foods = new HashMap<>();
    }

    public void addToTreatmentEmployee(Employee employee, Animal animal) {
        employee.addAnimal(animal);
    }

    public boolean removeFromTreatmentEmployee(Employee employee, Animal animal) {
        return employee.removeAnimal(animal);
    }

    public boolean feedAnimal(Employee employee, Animal animal, Food food, int amount) {
        if (amount <= animal.getMaxFoodPerKind(food)) {
            animal.updateFoodAmount(amount, food);
            return employee.feedAnimals(animal, food, amount);
        } else
            return false;
    }

    public void addFood(Food food) {
        this.foods.put(food, 0);
    }

    public boolean removeFood(Food food) {
        if (this.foods.containsKey(food)) {
            this.foods.remove(food);
            System.out.println("The food was removed successfully!");
            return true;
        }
        System.out.println("The food was not removed!!!!");
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
