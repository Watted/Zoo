package main.java.com.zoo.siraj;


import java.io.*;
import java.util.*;

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

    public List<Integer> getCages(){
        List<Integer> listOfInteger = new ArrayList<>();
        Iterator<Cage> iterator = this.cages.iterator();
        while (iterator.hasNext()){
            Cage next = iterator.next();
            listOfInteger.add(next.getSize());
        }
        return listOfInteger;
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
                if (animal.getMaxFood().containsKey(food)
                        && animal.getExistingFood().containsKey(food)
                        && animal.getEatenFood().containsKey(food)) {

                    int allowedFoodAmountToEatForAnimalPerDay = animal.getMaxFoodPerKind(food)
                            - animal.getExistingFoodPerKind(food)
                            - animal.getEatenFoodPerKind(food);

                    sumOfAllMissingAmount += allowedFoodAmountToEatForAnimalPerDay;
                }
            }
        }

        int existingAmount = this.foods.get(food);
        if (amountToBuy <= sumOfAllMissingAmount - existingAmount) {
            this.foods.put(food, amountToBuy + existingAmount);
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

    @Override
    public String toString() {
        return "Zoo{" +
                "cages=" + cages +
                ", employees=" + employees +
                ", foods=" + foods +
                '}';
    }
}
