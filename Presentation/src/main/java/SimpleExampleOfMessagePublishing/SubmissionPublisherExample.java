package SimpleExampleOfMessagePublishing;

/**
 * Created by sorincepoiscarletia on 3/26/18.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/*
* This is a simple example of a Flow
*
* In order to test the functionality please run the main method bellow
* */

public class SubmissionPublisherExample
{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        SubmissionPublisher<String> sb = new SubmissionPublisher<>(executor, Flow.defaultBufferSize());
        sb.subscribe(new MySubscriber());
        sb.submit("string 1");
        sb.submit("string 2");
        sb.submit("string 3");
        executor.shutdown();
    }
}
