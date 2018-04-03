package SpringWebFluxExample.server;

import SpringWebFluxExample.reactiveHandler.StudentHandler;
import SpringWebFluxExample.reactiveHandler.StudentHandlerImplementation;
import SpringWebFluxExample.repository.StudentRepository;
import SpringWebFluxExample.repository.StudentRepositoryImplementation;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

public class Server {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.startTomcatServer("localhost", 8080);
        System.out.println("Press ENTER to exit.");
        System.in.read();
    }

    public RouterFunction<ServerResponse> routingFunction() {
        StudentRepository repository = new StudentRepositoryImplementation();
        StudentHandler handler = new StudentHandlerImplementation(repository);

        return nest(path("/student"),
                nest(accept(APPLICATION_JSON),
                        route(GET("/{id}"), handler::getStudentFromRepository)
                                .andRoute(method(HttpMethod.GET), handler::getAllStudentsFromRepository)
                ).andRoute(POST("/")
                        .and(contentType(APPLICATION_JSON)), handler::saveStudentToRepository));
    }

    public void startTomcatServer(String host, int port) throws LifecycleException {
        RouterFunction<?> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setHostname(host);
        tomcatServer.setPort(port);
        Context rootContext = tomcatServer.addContext("", System.getProperty("java.io.tmpdir"));
        ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(httpHandler);
        Tomcat.addServlet(rootContext, "httpHandlerServlet", servlet);
        rootContext.addServletMapping("/", "httpHandlerServlet");
        tomcatServer.start();
    }
}
