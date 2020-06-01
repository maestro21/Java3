import java.util.Arrays;

public class Calculator {



    public int[] getLast4Array(int[] arr) throws RuntimeException {
        int pos = -1;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 4) {
                pos = i;
            }
        }

        if(pos == -1) {
            throw new RuntimeException("4 не найдено!");
        }

        return Arrays.copyOfRange(arr,pos + 1,arr.length);
    }



    public boolean checkOneAndFour(int[] arr) {
        boolean one = false;
        boolean four = false;

        for(int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case 1: one = true; break;
                case 4: four = true; break;
                default: return false;
            }
        }

        return one && four;
    }
}