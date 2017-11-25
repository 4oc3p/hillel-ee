package hillelee.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController()
public class PetController {
    private Map<Integer, Pet> pets = new HashMap<Integer, Pet>() {{
        put(0, new Pet("Tom", "Cat", 3));
        put(1, new Pet("Jerry", "Mouse", 2));
    }};
    private Integer counter = 1;

    @GetMapping("/greeting")
    public String helloWorld() {
        return "Hello world!";
    }

    @GetMapping("/pets")
    public List<Pet> getPets(@RequestParam Optional<String> specie,
                             @RequestParam Optional<Integer> age) {
        Predicate<Pet> specieFilter = specie
                .map(this::filterBySpecie)
                .orElse(pet -> true);

        Predicate<Pet> ageFilter = age
                .map(this::filterByAge)
                .orElse(pet -> true);

        Predicate<Pet> complexFilter = ageFilter.and(specieFilter);

        return pets.values().stream()
                .filter(complexFilter)
                .collect(Collectors.toList());
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        if (pets.size() <= id) {
            return ResponseEntity.badRequest().body(new ErrorBody("No pet with id = " + id));
        }
        return ResponseEntity.ok(pets.get(id));
    }

    @PostMapping("/pets")
    public ResponseEntity<Void> createPet(@RequestBody Pet pet) {
        pets.put(++counter, pet);
        return ResponseEntity.created(URI.create("/pets/" + counter)).build();
    }

    @PutMapping("/pets/{id}")
    public void updatePet(@PathVariable Integer id,
                          @RequestBody Pet pet) {
        pets.put(id, pet);
    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable Integer id) {
        if(!pets.containsKey(id)){
            throw new NoSuchPetException();
        }
        pets.remove(id);
    }

    private Predicate<Pet> filterBySpecie(String specie) {
        return pet -> pet.getSpecie().equals(specie);
    }

    private Predicate<Pet> filterByAge(Integer age) {
        return pet -> pet.getAge().equals(age);
    }


}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NoSuchPetException extends RuntimeException{
}

@Data
@AllArgsConstructor
class ErrorBody {
    private String message;
    private final Integer code = 400;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Pet {
    private String name;
    private String specie;
    private Integer age;
}
