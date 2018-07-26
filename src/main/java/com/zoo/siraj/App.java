package main.java.com.zoo.siraj;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Zoo zoo;
        try {
            zoo = Zoo.loadFromFile();
        } catch (Exception e) {
            zoo = new Zoo();
        }
        System.out.println(zoo.toString());

        Animal snake = new Snake("Lama",15);
        Animal monkey = new Monkey("Sa3eed",10);
        Animal lion = new Lion("Khaled",8);
        Cage cage = new Cage(15);
        zoo.addCage(cage);
        zoo.addAnimalToCage(snake,cage);
        zoo.addAnimalToCage(monkey,cage);
        zoo.addAnimalToCage(lion,cage);
        zoo.addFood(Food.meats);
        zoo.addFood(Food.fruits);
        lion.addFood(Food.meats,8);
        monkey.addFood(Food.meats,7);
        monkey.addFood(Food.fruits,3);
        snake.addFood(Food.meats,10);
        snake.addFood(Food.fruits,5);
        Employee employee = new Employee("Smadar");
        zoo.addEmployee(employee);
        zoo.addToTreatmentEmployee(employee,snake);
        zoo.addToTreatmentEmployee(employee,lion);
        zoo.addToTreatmentEmployee(employee,monkey);
        //True Positive
        test(zoo.feedAnimal(employee,lion,Food.meats,2),true);
//        test(zoo.feedAnimal(employee,monkey,Food.meats,6),true);
//        test(zoo.feedAnimal(employee,monkey,Food.fruits,2),true);
//        test(zoo.feedAnimal(employee,snake,Food.meats,10),true);
//        test(zoo.feedAnimal(employee,lion,Food.fruits,4),true);

        //False Positive
        test(zoo.feedAnimal(employee,lion,Food.fruits,88),false);
        test(zoo.feedAnimal(employee,monkey,Food.meats,543),false);
        test(zoo.feedAnimal(employee,monkey,Food.fruits,45),false);
        test(zoo.feedAnimal(employee,snake,Food.meats,234),false);
        test(zoo.feedAnimal(employee,lion,Food.fruits,1234),false);
        try {
            Zoo.saveToFile(zoo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void test(boolean b,boolean c) {
        if (b==c) System.out.println("Pass");
        else
            System.out.println("Fail");
    }

}
