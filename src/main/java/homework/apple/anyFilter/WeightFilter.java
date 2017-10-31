package homework.apple.anyFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */

@Data
@AllArgsConstructor
public class WeightFilter<T> implements AnySortInterface<T> {

    Integer weight;

    @Override
    @SneakyThrows
    public Boolean filter(T t) {
        Field weight = t.getClass().getDeclaredField("weight");
        weight.setAccessible(true);
        return this.weight < (Integer) weight.get(t);
    }
}
