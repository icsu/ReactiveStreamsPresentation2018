package SpringWebFluxExample.client;

import SpringWebFluxExample.core.Student;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public class Client {
    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.createStudent();
        client.getAllStudents();
    }

    public void createStudent() {
        URI uri = URI.create(String.format("http://%s:%d/student", HOST, PORT));
        Student student = new Student(13, "Sorin", "Cepoi", "Matematica");
        ClientRequest request = ClientRequest.method(HttpMethod.POST, uri)
                .body(BodyInserters.fromObject(student)).build();
        Mono<ClientResponse> response = exchange.exchange(request);
        System.out.println(response.block().statusCode());
    }

    public void getAllStudents() {
        URI uri = URI.create(String.format("http://%s:%d/student", HOST, PORT));
        ClientRequest request = ClientRequest.method(HttpMethod.GET, uri).build();
        Flux<Student> studentList = exchange.exchange(request)
                .flatMapMany(response -> response.bodyToFlux(Student.class));
        Mono<List<Student>> productListMono = studentList.collectList();
        System.out.println(productListMono.block());
    }
}
