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
          Integer max = maxFood.get(food);
          Integer existing = existingFood.get(food);
          if((max != null) && (existing !=null) &&( (existing + amount) <= max)) {
               existingFood.put(food,amount+existing);
          }
          else {
               throw  new RuntimeException("Something wrong while updating food");
          }
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public int getMaxFoodPerKind(Food food){
          if(maxFood.containsKey(food))
               return maxFood.get(food);
          else
               return 0;
     }

     public Map<Food, Integer> getExistingFood() {
          return existingFood;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;

          Animal animal = (Animal) o;

          return id.equals(animal.id);
     }

     @Override
     public int hashCode() {
          return id.hashCode();
     }

     public int getCageSize() {
          return cageSize;
     }

     public void setCageSize(int cageSize) {
          this.cageSize = cageSize;
     }
}
