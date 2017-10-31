package homework.apple.onlyAppleFilter;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import homework.apple.Apple;
import homework.apple.onlyAppleFilter.AppleFilter;
import homework.apple.onlyAppleFilter.ColorFilter;
import homework.apple.onlyAppleFilter.WeightFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */
public class AppleFilterTest {

    private List<Apple> apples;

    @Before
    public void fillApples() {
        this.apples = Arrays.asList(
                new Apple("RED", 1050),
                new Apple("RED", 1100),
                new Apple("GREEN", 1800),
                new Apple("GREEN", 1080),
                new Apple("RED", 1600),
                new Apple("RED", 1090),
                new Apple("RED", 1180)
        );
    }

    @Test
    public void colorFilterTest() throws Exception {
        List<Apple> filtered = new AppleFilter().filter(this.apples, new ColorFilter("RED"));
        assertThat(filtered, hasSize(5));
    }

    @Test
    public void weightFilterTest() throws Exception {
        List<Apple> filtered = new AppleFilter().filter(this.apples, new WeightFilter(1200));
        assertThat(filtered, hasSize(2));
    }
}