package com.zoo.siraj;

import java.util.HashMap;
import java.util.Map;

public abstract class Animal {
     private static int counter=1;
     private String name;
     private String id;
     private int cageSize;

     private Map<Food,Integer> maxFood = new HashMap<>();
     private Map<Food,Integer> existingFood = new HashMap<>();
     private Map<Food,Integer> eatenFood = new HashMap<>();

     public Animal(String name, int cageSize) {
          this.name = name;
          this.cageSize = cageSize;
          this.id = String.valueOf(counter++);
     }

     public void addFood(Food food, int maxFood) {
          this.maxFood.put(food,maxFood);
          this.existingFood.put(food,0);
          this.eatenFood.put(food,0);
     }
     public void removeFood(Food food){
          this.maxFood.remove(food);
          this.existingFood.remove(food);
          this.eatenFood.remove(food);
     }
     public void updateFoodAmount(int amount ,Food food){
          System.out.println();

     }


}
