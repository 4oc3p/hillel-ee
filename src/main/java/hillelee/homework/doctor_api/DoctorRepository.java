package hillelee.homework.doctor_api;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 4oc3p on 30.11.2017. hillelee
 */
@Repository
@Getter
public class DoctorRepository {
    private final Map<Integer, Doctor> doctors = new HashMap<>();

/*    public DoctorRepository() {
        doctors.put(1, new Doctor(1, "q", "w"));
        doctors.put(2, new Doctor(2, "qw", "we"));
        doctors.put(3, new Doctor(3, "aqw", "we"));
    }*/

    public Collection<Doctor> getAllDoctors() {
        return doctors.values();
    }

    public Optional<Doctor> getDoctorById(Integer id) {
        return Optional.ofNullable(doctors.get(id));
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctors.values().stream()
                .filter(doc -> doc.getSpecialization().equals(specialization))
                .collect(Collectors.toList());
    }

    public List<Doctor> getDoctorsByFirstLetterOfName(Character character) {
        return doctors.values().stream()
                .filter(doc -> doc.getName().startsWith(character.toString()))
                .collect(Collectors.toList());
    }


}
