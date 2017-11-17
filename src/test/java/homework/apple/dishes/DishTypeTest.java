package homework.apple.dishes;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by 4oc3p on 17.11.2017. hillel-ee
 */
public class DishTypeTest {
    Restaurant restaurant;

    @Before
    public void fill(){
        Dish apple = new Dish("Apple", 10, true, DishType.VEGETABLES);
        Dish peach = new Dish("Peach", 35, true, DishType.VEGETABLES);
        Dish beef = new Dish("Beef_1", 1000, false, DishType.BEEF);
        Dish beef2 = new Dish("Beef_2", 1200, false, DishType.BEEF);
        Dish chicken = new Dish("Chicken_1", 220, true, DishType.CHICKEN);
        Dish chicken2 = new Dish("Chicken_2", 550, false, DishType.CHICKEN);
        List<Dish> dishes = new ArrayList<>();
        dishes.add(apple);
        dishes.add(peach);
        dishes.add(beef);
        dishes.add(beef2);
        dishes.add(chicken);
        dishes.add(chicken2);
        restaurant = new Restaurant(dishes);
    }

    @Test
    public void one_a() throws Exception {
        restaurant.menu.stream()
                .filter(a -> a.calories < 300)
                .forEach(a -> System.out.println(a.name));
    }

    @Test
    public void one_b() throws Exception {
        restaurant.menu.stream()
                .sorted((a, b) -> b.calories.compareTo(a.calories))
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void one_c() throws Exception {
        restaurant.menu.stream()
                .sorted((a,b) -> a.isBio != b.isBio
                        ? b.isBio.compareTo(a.isBio)
                        : a.name.compareTo(b.name))
                .forEach(System.out::println);
    }

    @Test
    public void two() throws Exception {
        restaurant.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingDouble(Dish::getCalories)))
                .entrySet()
                .forEach(System.out::println);
    }

    @Test
    public void three() throws Exception {
        restaurant.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.toList(),
                                a -> a.stream()
                                        .filter(b -> b.isBio)
                                        .map(c -> c.name)
                                        .collect(Collectors.toList()))))
                .entrySet()
                .forEach(System.out::println);
//        Map<DishType, List<String>> q = new HashMap<>();
//        for (Dish d:restaurant.getMenu()) {
//            q.putIfAbsent(d.type, new ArrayList<String>());
//            if(d.isBio){
//                q.get(d.type).add(d.name);
//            }
//        }
//        q.entrySet().forEach(System.out::println);
    }
}