package hillelee.defaultMethods;

public interface Fruit {
    String getColor();

    Integer getWeight();

    default Double approximateVitaminC(){
        if(getColor().equals("GREEN")){
            return getWeight() * 0.01;
        } else {
            return getWeight() * 0.05;
        }
    }
}
