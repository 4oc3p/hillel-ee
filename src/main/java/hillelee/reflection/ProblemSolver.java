package hillelee.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class ProblemSolver {
    public String solve(Object problem) {
        return Stream.of(problem)
                .map(Object::getClass)
                .flatMap(clazz -> Arrays.stream(clazz.getMethods()))
                .filter(method -> method.isAnnotationPresent(CorrectAnswer.class))
                .map(invokeOn(problem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no CorrectAnswer annotation"));

    }

    private Function<Method, String> invokeOn(Object object) {
        return method -> {
            try {
                return ((String) method.invoke(object));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        };
    }
}
