package homework.apple.onlyAppleFilter;

import homework.apple.Apple;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */
public class AppleFilter {

    public List<Apple> filter(List<Apple> apples, AppleSortInterface app){
        return apples.stream().filter(app::filter).collect(Collectors.toList());
    }

}
