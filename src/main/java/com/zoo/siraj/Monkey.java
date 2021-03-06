package main.java.com.zoo.siraj;


import javax.swing.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Monkey extends Animal {
    private Timer timer;
    public Monkey(int cageSize) {
        super(cageSize);
    }

    @Override
    public void eat() {
        System.out.println("monkey");
        this.timer = new Timer(1000, action ->{
            boolean flag = false;
            Map<Food, Integer> existingFood = this.getExistingFood();
            Set<Map.Entry<Food, Integer>> entries = existingFood.entrySet();
            Iterator<Map.Entry<Food, Integer>> iterator = entries.iterator();
            System.out.println("size: " + entries.size());
            while (iterator.hasNext()){
                Map.Entry<Food, Integer> next = iterator.next();
                Integer value = next.getValue();
                if (value!=0){
                    System.out.println(value);
                    value -= 1;
                    Integer integer = this.eatenFood.get(next.getKey());
                    this.eatenFood.put(next.getKey(),integer+1);
                    next.setValue(value);
                    flag = true;
                    eating = true;
                }
            }

            if (!flag){
                eating = false;
                ((Timer)action.getSource()).stop();
            }
            System.out.println("eaten " + flag);
        });
        timer.start();
    }


    @Override
    public String getName() {
        return "Monkey";
    }
}
