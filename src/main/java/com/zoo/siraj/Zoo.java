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

    public void addFood(Food food){
        this.foods.put(food,0);
    }
    public Boolean removeFood(Food food){
        if(this.foods.containsKey(food)){
            this.foods.remove(food);
            System.out.println("The food was removed successfully!");
            return true;
        }
        System.out.println("The food was not removed!!!!");
        return false;
    }
    public Boolean buyFood(Food food,int amountToBuy){
        for(Cage cage:cages){
            if(cage.getContentAnimal().isEmpty()) {
               // cage.getContentAnimal().
            }
        }
        if(this.foods.containsKey(food)){
            int amountBeforeBuying=this.foods.get(food);
            this.foods.put(food,amountToBuy+amountBeforeBuying);
            System.out.println("The food was updated with the new amount!");
            return true;
        }
        System.out.println("The food amount was not bought!!!!");
        return false;
    }
}
