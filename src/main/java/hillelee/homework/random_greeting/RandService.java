package hillelee.homework.random_greeting;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 4oc3p on 23.11.2017. hillelee
 */
@Service
@Data
public class RandService {
    private final List<String> greetings = Arrays.asList("hello world", "hola world", "bonjour world");

    public String selectGreeting(){
        return greetings.get((int)(Math.random() * greetings.size()));
    }
}
