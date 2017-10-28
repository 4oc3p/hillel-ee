package hillelee.apple;


import hillelee.defaultMethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;

}



