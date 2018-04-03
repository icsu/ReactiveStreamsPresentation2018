package SpringWebFluxExample.reactiveHandler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


public interface StudentHandler {
     Mono<ServerResponse> getStudentFromRepository(ServerRequest request);

     Mono<ServerResponse> saveStudentToRepository(ServerRequest request);

     Mono<ServerResponse> getAllStudentsFromRepository(ServerRequest request);
}