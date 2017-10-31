package homework.apple.onlyAppleFilter;

import homework.apple.Apple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightFilter implements AppleSortInterface {

    private Integer weight;

    @Override
    public Boolean filter(Apple apple) {
        return apple.getWeight() > this.weight;
    }
}
