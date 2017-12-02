package hillelee.homework.doctor_api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Created by 4oc3p on 30.11.2017. hillelee
 */
@Service
@AllArgsConstructor
public class DoctorService {
    DoctorRepository doctorRepository;

    public void addDoctor(Doctor doctor) {
        Integer doctorId = doctorRepository.getDoctors().keySet().stream()
                .mapToInt(Integer::valueOf)
                .max()
                .orElse(-1);
        doctor.setId(++doctorId);
        doctorRepository.getDoctors().put(doctorId, doctor);
    }

    public void updateDoctor(Doctor doctor, Integer id) {
        doctor.setId(id);
        doctorRepository.getDoctors().put(id, doctor);
    }

    public void deleteDoctor(Integer id) {
        doctorRepository.getDoctors().remove(id);
    }

    public List<Doctor> filterDoctor(Collection<Doctor> doc, Optional<String> spec, Optional<Character> name) {
        Predicate<Doctor> filterBySpec = spec
                .map(this::filterBySpec)
                .orElse(pet -> true);

        Predicate<Doctor> filterByName = name
                .map(this::filterByName)
                .orElse(pet -> true);

        return doc.stream()
                .filter(filterByName)
                .filter(filterBySpec)
                .collect(Collectors.toList());
    }

    private Predicate<Doctor> filterBySpec(String specialization) {
        return doctor -> doctor.getSpecialization().equals(specialization);
    }

    private Predicate<Doctor> filterByName(Character name) {
        return doctor -> doctor.getName().startsWith(name.toString());
    }
}
