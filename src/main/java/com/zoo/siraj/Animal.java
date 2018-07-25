package com.zoo.siraj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Animal implements Serializable {
     abstract public void eat();

     private static int counter=1;
     private String name;
     private String id;
     private int cageSize;

     // remember this values are constants
     private Map<Food,Integer> maxFood = new HashMap<>();

     // remember this field updated peer every time the animal eat or max per 2 days
     private Map<Food,Integer> existingFood = new HashMap<>();

     // remember this values are reset every dat, so this is the food that eat beer day
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
     public void addFoodToAnimal(int amount , Food food){
          Integer max = maxFood.get(food);
          Integer existing = existingFood.get(food);
          Integer eaten = eatenFood.get(food);
          if((max != null) && (existing !=null) && (eaten !=null) && ((eaten + existing + amount) <= max)) {
               existingFood.put(food,amount+existing);
          }
          else {
               throw  new RuntimeException("Something wrong while updating food");
          }
     }
     public boolean resetEatenFood(Food food) {
          if (eatenFood.containsKey(food)) {
               eatenFood.put(food,0);
               return true;
          }
          else {
               return false;
          }
     }
     public void resetAllEatenFood() {
          for (Map.Entry<Food, Integer> entry : eatenFood.entrySet()) {
               entry.setValue(0);
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
     public int getExistingFoodPerKind(Food food){
          if(existingFood.containsKey(food))
               return existingFood.get(food);
          else
               return 0;
     }
     public int getEatenFoodPerKind(Food food){
          if(eatenFood.containsKey(food))
               return eatenFood.get(food);
          else
               return 0;
     }

     public Map<Food, Integer> getExistingFood() {
          return existingFood;
     }

     public Map<Food, Integer> getMaxFood() {
          return maxFood;
     }

     public Map<Food, Integer> getEatenFood() {
          return eatenFood;
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

     @Override
     public String toString() {
          return "\nAnimal{" +
                  "name='" + name + '\'' +
                  ", id='" + id + '\'' +
                  ", cageSize=" + cageSize +
                  ", maxFood=" + maxFood +
                  ", existingFood=" + existingFood +
                  ", eatenFood=" + eatenFood +
                  '}';
     }
}
