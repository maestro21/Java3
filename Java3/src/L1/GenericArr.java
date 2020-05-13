package L1;

import java.util.ArrayList;
import java.util.Arrays;

public class GenericArr<T> {

    private T[] arr;

    public GenericArr(T[] arr) {
        this.arr = arr;
    }

    public ArrayList<T> getList() {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public void swap(int i, int j) {
        swap(arr, i, j);
    }

    private void swap(T[] a, int i, int j) {

        if (i >= arr.length || j >= arr.length || i < 0 || j < 0) {
            System.out.println("Index out of boundaries");
            return;
        }

        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public String toString() {
        return Arrays.toString(arr);
    }

}
