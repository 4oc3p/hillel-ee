package hillelee.knight;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

public class FairyTale {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("hillelee");

        System.out.println(ctx.getBean("knight"));

        Knight knight1 = ctx.getBean(Knight.class);
        Knight knight2 = ctx.getBean(Knight.class);

        System.out.println(knight1 == knight2);
    }
}

@Configuration
class Config {
    @Bean
    public Knight knight( /* @Qualifier("killDragon") */ Quest quest) {
        return new Knight(quest);
    }

    @Bean
    Quest killDragon() {
        return new Quest("Kill dragon");
    }

    @Bean
    Quest rescuePrincess() {
        return new Quest("Rescue princess");
    }

    @Bean
    String task(){
        return "do nothing";
    }
}

@Data
//@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Knight {
    private final Quest quest;
}

@Data
@Component
class Quest {
    private final String task;
}