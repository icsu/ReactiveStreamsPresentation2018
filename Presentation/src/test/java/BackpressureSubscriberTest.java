import ControllingDemandOfMessages_Backpressure.BackpressureSubscriber;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/*
This is a test case created for class BackpressureSubscriber in order to demonstrate how the backpressure concept works in Reactive Streams API
Let’s say that we want to consume two elements from the Subscription, apply some logic and finish processing. We can use the request() method to achieve this.
In order to achieve this we added a constructor on BackpressureSubscriber class and we’ll be passing that number as the howMuchMessagesConsume constructor argument:
* */


public class BackpressureSubscriberTest
{

    @Test
    public void whenRequestForOnlyOneElement_thenShouldConsumeOne()
            throws InterruptedException {

        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        BackpressureSubscriber<String> subscriber = new BackpressureSubscriber<>(2);
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");
        List<String> expected = List.of("1","x");

        // when
        assertThat(publisher.getNumberOfSubscribers(), is(1));
        items.forEach(publisher::submit);
        publisher.close();

        // then
        await().atMost(1000, TimeUnit.MILLISECONDS)
                .until(() ->
                        assertThat(subscriber.consumedElements.size(), is(expected.size()))
                );
    }
}
