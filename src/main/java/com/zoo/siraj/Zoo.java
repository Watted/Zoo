package main.java.com.zoo.siraj;


import java.io.*;
import java.util.*;

public class Zoo implements Serializable {
    private Map<String,Cage> cages;
    private Map<String,Employee> employees;
    private Map<Food, Integer> foods;
    private Map<String,Animal> animals;

    public Zoo() {
        cages = new HashMap<>();
        employees = new HashMap<>();
        foods = new HashMap<>();
        animals = new HashMap<>();
    }

    public void addCage(Cage cage) {
        cages.put(cage.getId(),cage);
    }

    public List<Integer> getCages(){
        List<Integer> listOfInteger = new ArrayList<>();
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            listOfInteger.add(next.getValue().getSize());
        }
        return listOfInteger;
    }
    public List<String> getEmployees(){
        List<String> listOfInteger = new ArrayList<>();
        Set<Map.Entry<String, Employee>> entries = this.employees.entrySet();
        Iterator<Map.Entry<String, Employee>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Employee> next = iterator.next();
            listOfInteger.add(next.getValue().getId());
        }
        return listOfInteger;
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

    /*public boolean buyFood(Food food, int amountToBuy) {
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
    }*/

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


    public Cage getCageWithThisSize(int selectedItem) {
        Set<Map.Entry<String, Cage>> entries = this.cages.entrySet();
        Iterator<Map.Entry<String, Cage>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Cage> next = iterator.next();
            Cage value = next.getValue();
            if (selectedItem==value.getSize()){
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
}
