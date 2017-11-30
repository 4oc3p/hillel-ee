package hillelee.homework.random_greeting;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 4oc3p on 23.11.2017. hillelee
 */
@RestController
@AllArgsConstructor
public class RandController {
    private final RandService randService;

    @GetMapping("/random_greeting")
    public String randomGreeting(){
        return randService.selectGreeting();
    }
}
