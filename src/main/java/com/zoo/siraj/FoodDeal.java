package main.java.com.zoo.siraj;

import java.io.Serializable;

public class FoodDeal implements Serializable {
    private Food current;
    private Integer amount;

    public FoodDeal(Food current, Integer amount) {
        this.current = current;
        this.amount = amount;
    }

    public Food getCurrent() {
        return current;
    }

    public Integer getAmount() {
        return amount;
    }
}
