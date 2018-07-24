package com.zoo.siraj;

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
        lion.addFood(Food.meats,8);
        monkey.addFood(Food.meats,7);
        monkey.addFood(Food.fruits,3);
        snake.addFood(Food.meats,10);
        snake.addFood(Food.fruits,5);
        Employee employee = new Employee("Smadar");
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
        System.out.println();
    }

    private static void test(boolean b,boolean c) {
        if (b==c) System.out.println("Pass");
        else
            System.out.println("Fail");
    }

}
