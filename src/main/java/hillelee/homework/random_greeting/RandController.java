package hillelee.homework.random_greeting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 4oc3p on 23.11.2017. hillelee
 */
@RestController
@Data
public class RandController {
    private final RandService randService;

    @Autowired
    public RandController(RandService randService) {
        this.randService = randService;
    }

    @GetMapping("/random_greeting")
    public String randomGreeting(){
        return randService.selectGreeting();
    }
}
