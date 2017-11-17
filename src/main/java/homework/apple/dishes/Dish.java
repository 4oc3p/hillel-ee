package homework.apple.dishes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by 4oc3p on 17.11.2017. hillel-ee
 */
@Data
@AllArgsConstructor
public class Dish {
    String name;
    Integer calories;
    Boolean isBio;
    DishType type;
}

enum DishType {
    BEEF,
    CHICKEN,
    VEGETABLES
}

@Data
@AllArgsConstructor
class Restaurant {
    List<Dish> menu;
}

