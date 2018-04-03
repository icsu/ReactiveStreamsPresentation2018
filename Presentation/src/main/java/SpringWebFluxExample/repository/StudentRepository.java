package SpringWebFluxExample.repository;

import SpringWebFluxExample.core.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {

    Mono<Student> getStudent(int id);

    Flux<Student> getAllStudents();

    Mono<Void> saveStudent(Mono<Student> student);

}
