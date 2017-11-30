package hillelee.homework.doctor_api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by 4oc3p on 30.11.2017. hillelee
 */
@RestController
@AllArgsConstructor
public class DoctorController {
    private DoctorRepository doctorRepository;
    private DoctorService doctorService;


    @GetMapping(value = "/doctors")
    public ResponseEntity<Collection<Doctor>> getAllDoctors(@RequestParam Optional<String> specialization,
                                                            @RequestParam Optional<Character> name) {
        return ResponseEntity.ok(
                specialization.isPresent()
                        ? doctorRepository.getDoctorsBySpecialization(specialization.get())
                        : name.isPresent()
                        ? doctorRepository.getDoctorsByFirstLetterOfName(name.get())
                        : doctorRepository.getAllDoctors()
        );
    }

    @GetMapping(value = "/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorByID(@PathVariable Integer id) {
        Optional<Doctor> doctorById = doctorRepository.getDoctorById(id);
        return doctorById.isPresent()
                ? ResponseEntity.ok(doctorById.get())
                : ResponseEntity.status(404).build();
    }

    @PostMapping(value = "/doctors")
    public ResponseEntity<Void> addDoctor(@RequestBody Doctor doctor) {
        if (doctor.id != null) {
            return ResponseEntity.status(400).build();
        }
        doctorService.addDoctor(doctor);
        return ResponseEntity.created(URI.create("/doctors/" + doctor.id)).build();
    }

    @PutMapping(value = "/doctors/{id}")
    public ResponseEntity<Void> updateDoctor(@RequestBody Doctor doctor,
                                             @PathVariable Integer id) {
        if (!doctorRepository.getDoctorById(id).isPresent()) {
            return ResponseEntity.status(404).build();
        }
        if (doctor.id != null) {
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
