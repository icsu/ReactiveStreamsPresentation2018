package SpringWebFluxExample.reactiveHandler;

import SpringWebFluxExample.core.Student;
import SpringWebFluxExample.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class StudentHandlerImplementation implements StudentHandler
{

    private final StudentRepository studentRepository;

    public StudentHandlerImplementation(StudentRepository studentRepository){
        this.studentRepository  =studentRepository;
    }

    @Override public Mono<ServerResponse> getStudentFromRepository(ServerRequest request)
    {
        int studentId = Integer.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Student> studentMono = this.studentRepository.getStudent(studentId);

        return studentMono.flatMap(student -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(student)))
                .switchIfEmpty(notFound);
    }

    @Override public Mono<ServerResponse> saveStudentToRepository(ServerRequest request)
    {
        Mono<Student> student = request.bodyToMono(Student.class);
        return ServerResponse.ok().build(this.studentRepository.saveStudent(student));
    }

    @Override public Mono<ServerResponse> getAllStudentsFromRepository(ServerRequest request)
    {
        Flux<Student> students = this.studentRepository.getAllStudents();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(students,Student.class);
    }
}
