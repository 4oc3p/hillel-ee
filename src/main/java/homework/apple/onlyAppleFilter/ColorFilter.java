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
public class ColorFilter implements AppleSortInterface {

    private String color;

    @Override
    public Boolean filter(Apple apple) {
        return apple.getColor().equals(color);
    }
}
