package hillelee.homework.doctor_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 4oc3p on 30.11.2017. hillelee
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Integer id;
    private String name;
    private String specialization;
}
