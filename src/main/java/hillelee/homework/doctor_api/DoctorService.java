package hillelee.homework.doctor_api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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
}
