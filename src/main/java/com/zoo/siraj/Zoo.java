package main.java.com.zoo.siraj;


import UX.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class Zoo implements Serializable {
    private Map<String,Cage> cages;
    private Map<String,Employee> employees;
    private Map<Food, Integer> foods;
    private Map<String,Animal> animals;
    private Map<String,FoodDeal> dealsPerDate;
    public Zoo() {
        cages = new HashMap<>();
        employees = new HashMap<>();
        foods = new HashMap<>();
        animals = new HashMap<>();
        dealsPerDate = new HashMap<>();
        for (Food food : Food.values()) {
            foods.put(food,0);
        }
    }

    public Map<String, FoodDeal> getDealsPerDate() {
        return dealsPerDate;
    }

    public Map<Food, Integer> getFoods() {
        return foods;
    }

    public void addCage(Cage cage) {
        cages.put(cage.getId(),cage);
    }
    public Map<String,Cage> getCagesMap(){
        return  cages;
    }

    public List<String> getCages(){
        List<String> listOfInteger = new ArrayList<>();
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            listOfInteger.add("Id: "+ next.getKey()+", Size: "+ next.getValue().getSize());
        }
        return listOfInteger;
    }
    public List<String> getEmployees(){
        List<String> listOfInteger = new ArrayList<>();
        Set<Map.Entry<String, Employee>> entries = this.employees.entrySet();
        Iterator<Map.Entry<String, Employee>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Employee> next = iterator.next();
            listOfInteger.add("Id: "+ next.getValue().getId()+", name: "+next.getValue().getName());
        }
        return listOfInteger;
    }
    public Map<String,Employee> getEmployeesMap(){
        return employees;
    }
    public List<String> getAnimals(String animal){
        List<String> listOfAnimal = new ArrayList<>();
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            Iterator<Animal> animalIterator = next.getValue().getContentAnimal().iterator();
            while (animalIterator.hasNext()){
                Animal animal1 = animalIterator.next();
                if (animal.equals("Lion") && animal1 instanceof Lion){
                    listOfAnimal.add(animal1.getId());
                }else if (animal.equals("Monkey") && animal1 instanceof Monkey){
                    listOfAnimal.add(animal1.getId());
                }else if (animal.equals("Snake") && animal1 instanceof Snake){
                    listOfAnimal.add(animal1.getId());
                }

            }
        }
        return listOfAnimal;
    }

    public void removeCage(String id) {
        Cage remove = cages.remove(id);
        List<Animal> contentAnimal = remove.getContentAnimal();
        Iterator<Animal> iterator = contentAnimal.iterator();
        while (iterator.hasNext()){
            Animal next = iterator.next();
            this.animals.remove(next);
        }

    }


    public void addAnimalToCage(Animal animal, Cage cage) {
        cage.addAnimal(animal);
        this.animals.put(animal.getId(),animal);
    }



    public void removeAnimalById(String id){
        Animal animal = this.animals.remove(id);
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            Cage value = next.getValue();
            value.removeAnimal(animal);
        }
    }

    public Animal getAnimalById(String id){
        return this.animals.get(id);
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(),employee);
    }

    public Employee removeEmployee(Employee employee) {
        return employees.remove(employee.getId());
    }

//    public void addFood(Food food) {
//        this.foods.put(food, 0);
//    }

    public boolean removeFood(Food food) {
        if (this.foods.containsKey(food)) {
            this.foods.remove(food);
            return true;
        }
        return false;
    }

    public boolean buyFood(Food food, int amountToBuy,String time) {
        boolean toEat = false;
        for (Animal animal : animals.values()) {
            for (Food food1 : animal.getExistingFood().keySet()) {
                if(food.equals(food1)) {
                    toEat = true;
                    break;
                }
            }
        }
        if(toEat){
            this.foods.put(food,this.foods.get(food)+amountToBuy);
            this.dealsPerDate.put(time,new FoodDeal(food,amountToBuy));
            Main.dealNumber++;
            return true;
        }
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
            animal.eat();
            return employee.feedAnimals(animal, food, amount);
        }
        else {
            return false;
        }
    }


    public static void saveToFile(Zoo zoo) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("Zoo.ser"));
        stream.writeObject(zoo);
        stream.close();
    }

    public static Zoo loadFromFile() throws Exception {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("Zoo.ser"));
        Zoo zoo =(Zoo) stream.readObject();
        stream.close();
        return zoo;
    }
    public static Zoo loadZoo() {
        try {
            return loadFromFile();
        }
        catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Created new Zoo Successfully !");
                alert.showAndWait();
            });

            return new Zoo();
        }
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "cages=" + cages +
                ", employees=" + employees +
                ", foods=" + foods +
                '}';
    }

    public Cage getCageToThisAnimal(Animal animalById) {
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            Cage value = next.getValue();
            if (value.contentThisAnimal(animalById)){
                return value;
            }
        }
        return null;
    }

    public Employee getEmployeeToThisAnimal(Animal animalById) {
        Set<Map.Entry<String, Employee>> entries = this.employees.entrySet();
        Iterator<Map.Entry<String, Employee>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Employee> next = iterator.next();
            Employee value = next.getValue();
            if (value.contentThisAnimal(animalById)){
                return value;
            }
        }
        return null;
    }


    public Employee getEmployeeWithThisId(String id) {
        return this.employees.get(id);
    }

    public Cage getCageForThisId(String parseInt) {
        return this.cages.get(parseInt);
    }

    public Map<Food, Integer> getFood() {
        return this.foods;
    }

    public void resetAnimalBellies() {
        for (Animal animal : animals.values()) {
            animal.resetAllEatenFood();
        }
    }

    public void removeFoodAmount(FoodDeal foodDeal) {
        Food f = foodDeal.getCurrent();
        int am = foodDeal.getAmount();
        int current = this.foods.get(f);
        this.foods.put(f,Math.max(0,current-am));
    }

    public Collection<Animal> getAnimalsHash() {
        return animals.values();
    }
}
