package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class AppleSelectorTest {
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

        List<Apple> filtered = AppleSelector.filter(apples, new ApplePredicate() {
            @Override
            public Boolean test(Apple apple) {
                return apple.getWeight() > 1500;
            }
        });
        assertThat(filtered, hasSize(2));
    }
}