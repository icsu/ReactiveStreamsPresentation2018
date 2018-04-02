package SpringWebFluxExample.repository;

import SpringWebFluxExample.core.Student;
import SpringWebFluxExample.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepositoryImplementation implements StudentRepository
{

    private final Map<Integer,Student> studentMap = new ConcurrentHashMap<>();

    public StudentRepositoryImplementation(){
        this.studentMap.put(1, new Student(10,
                "Ionica",
                "Prenume",
                "Matematica"));

        this.studentMap.put(2, new Student(11,
                "Ioana",
                "Maria",
                "Informatica"));
    }

    @Override public Mono<Student> getStudent(int id)
    {
        return Mono.justOrEmpty(this.studentMap.get(id));
    }

    @Override public Flux<Student> getAllStudents()
    {
        return Flux.fromIterable(this.studentMap.values());
    }

    @Override public Mono<Void> saveStudent(Mono<Student> studentMono)
    {
        Mono<Student> sMono = studentMono.doOnNext(student -> {
            int id = studentMap.size() + 1;
            studentMap.put(id,student);
            System.out.format("SAved %s with id %d%n", student, id);
        });
        return sMono.thenEmpty(Mono.empty());
    }
}
