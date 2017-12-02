package hillelee.homework.doctor_api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by 4oc3p on 30.11.2017. hillelee
 */
@RestController
@AllArgsConstructor
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;


    @GetMapping(value = "/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam Optional<String> specialization,
                                                      @RequestParam Optional<Character> name) {
        return ResponseEntity.ok(doctorService.filterDoctor(specialization, name));
    }

    @GetMapping(value = "/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorByID(@PathVariable Integer id) {
        return doctorRepository.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping(value = "/doctors")
    public ResponseEntity<Void> addDoctor(@RequestBody Doctor doctor) {
        if (doctor.getId() != null) {
            return ResponseEntity.status(400).build();
        }
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.created(URI.create("/doctors/" + savedDoctor.getId())).build();
    }

    @PutMapping(value = "/doctors/{id}")
    public ResponseEntity<Void> updateDoctor(@RequestBody Doctor doctor,
                                             @PathVariable Integer id) {
        if (!doctorRepository.getDoctorById(id).isPresent()) {
            return ResponseEntity.status(404).build();
        }
        if (doctor.getId() != null) {
            return ResponseEntity.status(400).build();
        }
        doctorService.updateDoctor(doctor, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(value = "/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        if (!doctorRepository.getDoctorById(id).isPresent()) {
            return ResponseEntity.status(404).build();
        }
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(204).build();
    }
}
