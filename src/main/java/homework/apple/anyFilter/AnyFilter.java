package homework.apple.anyFilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 4oc3p on 31.10.2017. hillel-ee
 */
public class AnyFilter<T> {

    public List<T> filter(List<T> list, AnySortInterface<T> sort){
        return list.stream().filter(sort::filter).collect(Collectors.toList());
    }
}
