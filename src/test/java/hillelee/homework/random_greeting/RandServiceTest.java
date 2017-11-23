package hillelee.homework.random_greeting;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by 4oc3p on 23.11.2017. hillelee
 */
public class RandServiceTest {
    private RandService rnd = new RandService();
    private Map<String, Integer> normDist = new HashMap<>();

    @Test
    public void normalDistribution() throws Exception {
        int iterCount = 100;
        for (int i = 0; i < iterCount; i++) {
            String greeting = rnd.selectGreeting();
            normDist.putIfAbsent(greeting, 0);
            normDist.put(greeting, normDist.get(greeting) + 1);
        }

        Integer var = 3;
        Integer norm = iterCount / normDist.values().size();

        int count = (int) normDist.values().stream()
                .map(a -> a >= norm - var && a <= norm + var)
                .filter(a -> !a)
                .count();

        assertThat(count, is(0));
    }
}