package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class AppleSelectorTest {

    List<Apple> apples = ImmutableList.of(
            new Apple("Red", 1050),
            new Apple("Red", 1100),
            new Apple("Green", 1800),
            new Apple("Red", 1080),
            new Apple("Green", 1600),
            new Apple("Red", 1090),
            new Apple("Red", 1180)
    );

    @Test
    public void selectHeaviest() throws Exception {
        List<Apple> apples = ImmutableList.of(
                new Apple("Red", 1050),
                new Apple("Red", 1100),
                new Apple("Red", 1800),
                new Apple("Red", 1080),
                new Apple("Red", 1600),
                new Apple("Red", 1090),
                new Apple("Red", 1180)
        );
        Optional<Apple> mayBeHeaviest = AppleSelector.getHeaviest(apples);
        if (mayBeHeaviest.isPresent()) {
            Apple heaviest = mayBeHeaviest.get();
            assertThat(heaviest.getWeight(), is(1800));
        } else {
            fail();
        }
    }

    @Test
    public void selectHeaviestFromEmptyList() {
        List<Apple> apples = ImmutableList.of();

        Optional<Apple> mayBeHeaviest = AppleSelector.getHeaviest(apples);
        if (mayBeHeaviest.isPresent()) {
            fail();
        }
    }

    @Test
    public void sort() throws Exception {
        List<Apple> apples = ImmutableList.of(
                new Apple("Red", 1050),
                new Apple("Red", 1100),
                new Apple("Red", 1800),
                new Apple("Red", 1080),
                new Apple("Red", 1600),
                new Apple("Red", 1090),
                new Apple("Red", 1180)
        );

        apples = new ArrayList<>(apples);

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }

    @Test
    public void mapDefault() throws Exception {
        Map<String, Integer> nameToAge = ImmutableMap.of("Anton", 25);

        Integer age = nameToAge.getOrDefault("Valera", 0);
    }

    @Test
    public void filterByPredicate() throws Exception {
        List<Apple> apples = ImmutableList.of(
                new Apple("Red", 1050),
                new Apple("Red", 1100),
                new Apple("GREEN", 1800),
                new Apple("GREEN", 1080),
                new Apple("Red", 1600),
                new Apple("Red", 1090),
                new Apple("Red", 1180)
        );

        apples = new ArrayList<>(apples);

        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());
        assertThat(filtered, hasSize(2));
    }

    @Test
    public void filterByAnonymousPredicate() throws Exception {
        List<Apple> apples = ImmutableList.of(
                new Apple("Red", 1050),
                new Apple("Red", 1100),
                new Apple("GREEN", 1800),
                new Apple("GREEN", 1080),
                new Apple("Red", 1600),
                new Apple("Red", 1090),
                new Apple("Red", 1180)
        );

        apples = new ArrayList<>(apples);

//        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getWeight() > 120);

        Predicate<Apple> heavierThan120 = apple -> apple.getWeight() > 1200;
        Predicate<Apple> isRed = apple -> apple.getColor().equals("Red");
        Predicate<Apple> heavyAndRed = isRed.and(heavierThan120);

        List<Apple> filtered = apples.stream()
                .filter(heavyAndRed)
                .collect(Collectors.toList());

        assertThat(filtered, hasSize(1));
    }

    @Test
    public void mapToColor() throws Exception {
        List<String> colors = this.apples.stream()
                .map(Apple::getColor)
                .collect(Collectors.toList());
        assertThat(colors, hasSize(7));
        assertThat(colors.get(0), is("Red"));
    }

    @Test
    public void printApples() throws Exception {
        this.apples.forEach(System.out::println);
    }

    @Test
    public void adjustColor() throws Exception {
        ColorAdjuster colorAdjuster = new ColorAdjuster();
//        Apple apple = apples.stream().filter(a -> a.equals(red)).findFirst().orElse(null);
//        System.out.println(apple);
        apples.stream()
                .map(Apple::getColor)
                .map(colorAdjuster::adjust)
                .forEach(System.out::println);
    }

    @Test
    public void executionFlow() throws Exception {
        List<String> collect = apples.stream()
                .filter(apple -> apple.getWeight() > 100)
                .map(Apple::getColor)
                .peek(System.out::println)
                .limit(3)
                .collect(Collectors.toList());
    }

    @Test
    public void findFirst() throws Exception {
        apples.stream()
                .filter(a -> a.getWeight() > 59)
                .findFirst()
                .map(Apple::getColor)
                .ifPresent(System.out::println);
    }

    @Test
    public void intStream() throws Exception {
        IntSummaryStatistics ints = apples.stream()
                .mapToInt(Apple::getWeight)
                .summaryStatistics();
        System.out.println(ints);
    }

    @Test
    public void toMap() throws Exception {
        Map<Integer, Apple> weightToApple = apples.stream()
                .collect(Collectors.toMap(Apple::getWeight, Function.identity()));

        assertThat(weightToApple.get(1050), is(apples.get(0)));
    }

    @Test
    public void groupingById() throws Exception {
        Map<String, List<Apple>> collect = apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.toList()));
        System.out.println(collect.get("Green"));
    }

    @Test
    public void getAllWorms() throws Exception {
        List<String> collect = apples.stream()
                .flatMap(a -> a.getWorms().stream())
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}