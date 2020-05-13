package L1;

public class Fruit implements Cloneable{

    protected Float weight;

    public Float getWeight() {
        return weight;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
