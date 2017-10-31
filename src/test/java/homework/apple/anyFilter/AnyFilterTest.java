package homework.apple.anyFilter;

import homework.apple.Apple;
import homework.apple.Peach;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */
public class AnyFilterTest {

    private List<Peach> peaches;
    private List<Apple> apples;

    @Before
    public void fillPeaches() {
        this.peaches = Arrays.asList(
                new Peach("RED", 1050),
                new Peach("RED", 1100),
                new Peach("GREEN", 1800),
                new Peach("GREEN", 1080),
                new Peach("RED", 1600),
                new Peach("RED", 1090),
                new Peach("RED", 1180)
        );
    }

    @Before
    public void fillApples() {
        this.apples = Arrays.asList(
                new Apple("RED", 1050),
                new Apple("RED", 1100),
                new Apple("GREEN", 1800),
                new Apple("GREEN", 1080),
                new Apple("RED", 1600),
                new Apple("RED", 1090),
                new Apple("RED", 1280)
        );
    }

    @Test
    public void filterPeachWeight() throws Exception {
        List<Peach> filtered = new AnyFilter<Peach>().filter(peaches, new WeightFilter<>(1200));
        assertThat(filtered, hasSize(2));
    }

    @Test
    public void filterAppleWeight() throws Exception {
        List<Apple> filtered = new AnyFilter<Apple>().filter(apples, new WeightFilter<>(1200));
        assertThat(filtered, hasSize(3));
    }
}