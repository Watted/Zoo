package main.java.com.zoo.siraj;

public class FoodDeal {
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
