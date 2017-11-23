package hillelee.homework.random_greeting;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 4oc3p on 23.11.2017. hillelee
 */
public class RandServiceTest {
    private RandService rnd = new RandService();
    private Map<String, Integer> normDist = new HashMap<>();

    @Test
    public void normalDistribution() throws Exception {
        for (int i = 0; i < 100; i++) {
            String greeting = rnd.selectGreeting();
            normDist.putIfAbsent(greeting, 0);
            normDist.put(greeting, normDist.get(greeting) + 1);
        }
        normDist.forEach((key, value) -> System.out.println(key + " selected " + value + " times"));
    }
}