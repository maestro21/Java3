package L1;

import java.util.ArrayList;
import java.util.Arrays;

public class L1 {


    public static void main(String[] args) {
        taskOneAndTwo();
        taskThree();
    }

    private static void taskOneAndTwo() {
        System.out.println("Task1: method that swaps elements");
        String[] testArr = {
                "Str1",
                "Str2",
                "Str3"
        };
        GenericArr<String> genericArr = new GenericArr<String>(testArr);
        System.out.println("Starting array:" + genericArr);

        genericArr.swap(0, 2);
        System.out.println("Swapped array:" + genericArr);
        genericArr.swap(0, 10);

        ArrayList<String> arrayList = genericArr.getList();
        System.out.println("Printed ArrayList:" + Arrays.toString(arrayList.toArray()));
    }

    private static void taskThree() {
        System.out.println("Task3: create two boxes of fruits and compare them");
        Box<Apple> appleBox = new Box();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        Box<Orange> orangeBox = new Box();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.printf("Compare apple box weight (%s) to orange box (%s): %b\n", appleBox.getWeight(), orangeBox.getWeight(), appleBox.compare(orangeBox));

        System.out.println("Creating new boxes and pouring fruits from old boxes to new");
        Box<Apple> appleBox2 = new Box();
        Box<Orange> orangeBox2 = new Box();
        appleBox.pour(appleBox2);
        orangeBox.pour(orangeBox2);
        System.out.println("Old apple box:" + appleBox);
        System.out.println("New apple box:" + appleBox2);
        System.out.println("Old orange box:" + orangeBox);
        System.out.println("New orange box:" + orangeBox2);

        System.out.printf("Compare apple box weight (%s) to orange box (%s): %b\n", appleBox.getWeight(), orangeBox.getWeight(), appleBox.compare(orangeBox));
    }
}
