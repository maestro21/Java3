package L1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits = new ArrayList<T>();

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public void add(T el) {
        fruits.add(el);
    }

    public void pour(Box<T> box) {
        for (int i = 0; i < getFruits().size(); i++) {
            box.getFruits().add(fruits.get(i));
        }
        fruits.clear();
    }

    public boolean compare(Box box) {
        return getWeight().equals(box.getWeight());
    }

    public String getWeight() {
        Float weight = 0.0f;
        for (int i = 0; i < getFruits().size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return String.format("%,.2f", weight);
    }

    public String toString() {
        return Arrays.toString(fruits.toArray());
    }

}
