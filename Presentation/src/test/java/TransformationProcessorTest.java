import TransformationOfMessage.TransformProcessor;
import TransformationOfMessage.TransformationSubscriber;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*
This test case was created to demonstrate how you can use a Transformer between your Publisher and Subscriber.

We’ll create the TransformProcessor class that implements Processor and extends SubmissionPublisher – as this will be both Publisher and Subscriber.

We’ll pass in a Function that will transform inputs into outputs.

*/
public class TransformationProcessorTest
{
    @Test
    public void whenSubscribeAndTransformElements_thenShouldConsumeAll()
            throws InterruptedException {

        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformProcessor<String, Integer> transformProcessor
                = new TransformProcessor<>(Integer::parseInt);
        TransformationSubscriber<Integer> subscriber = new TransformationSubscriber<>();
        List<String> items = List.of("1", "2", "3");
        List<Integer> expectedResult = List.of(1, 2, 3);

        // when
        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);
        items.forEach(publisher::submit);
        publisher.close();

        // then
        await().atMost(1000, TimeUnit.MILLISECONDS)
                .until(() -> assertThat(subscriber.consumedElements.size(), is(items.size()))
                );
    }
}
