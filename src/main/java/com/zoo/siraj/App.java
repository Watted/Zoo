package com.zoo.siraj;

import java.io.FileOutputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Zoo zoo = new Zoo();
        Animal snake = new Snake("Lama",15);
        Animal monkey = new Monkey("Sa3eed",10);
        Animal lion = new Lion("Khaled",8);
        lion.addFood(Food.meat,8);
        monkey.addFood(Food.meat,7);
        monkey.addFood(Food.fruit,3);
        snake.addFood(Food.meat,10);
        snake.addFood(Food.fruit,5);
        Employee employee = new Employee("Smadar");
        zoo.addToTreatmentEmployee(employee,snake);
        zoo.addToTreatmentEmployee(employee,lion);
        zoo.addToTreatmentEmployee(employee,monkey);
        //True Positive
        test(zoo.feedAnimal(employee,lion,Food.meat,2),true);
//        test(zoo.feedAnimal(employee,monkey,Food.meat,6),true);
//        test(zoo.feedAnimal(employee,monkey,Food.fruit,2),true);
//        test(zoo.feedAnimal(employee,snake,Food.meat,10),true);
//        test(zoo.feedAnimal(employee,lion,Food.fruit,4),true);

        //False Positive
        test(zoo.feedAnimal(employee,lion,Food.fruit,88),false);
        test(zoo.feedAnimal(employee,monkey,Food.meat,543),false);
        test(zoo.feedAnimal(employee,monkey,Food.fruit,45),false);
        test(zoo.feedAnimal(employee,snake,Food.meat,234),false);
        test(zoo.feedAnimal(employee,lion,Food.fruit,1234),false);
        System.out.println();
    }

    private static void test(boolean b,boolean c) {
        if (b==c) System.out.println("Pass");
        else
            System.out.println("Fail");
    }

}
