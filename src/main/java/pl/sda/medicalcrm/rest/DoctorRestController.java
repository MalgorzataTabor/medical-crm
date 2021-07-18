package pl.sda.medicalcrm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.medicalcrm.entity.Doctor;
import pl.sda.medicalcrm.entity.User;
import pl.sda.medicalcrm.service.DoctorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctors")
//@CrossOrigin(origins = "http://localhost:4200")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public
    Long registerNewDoctor(@RequestBody @Valid Doctor doctor) {
        return doctorService.registerNewDoctor(doctor);
    }

    @GetMapping
    public
    List<User> getAllDoctorsList(){
        return doctorService.getAllDoctorsList();
    }

    @PutMapping(path = "/{userId}")
    public Long changeDoctorData(@PathVariable Long userId,
                                                      @RequestBody @Valid Doctor doctor) {
        return doctorService.changeDoctorData(userId, doctor);
    }

    @GetMapping(path = "/{userId}")
    public
    Doctor getDoctorData(@PathVariable Long userId) {
        return doctorService.getDoctorData(userId);
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteDoctor (@PathVariable Long userId) {
        return doctorService.deleteDoctor(userId);
    }


}
