import PublishingAndConsumingMessages.PublishingAndConsumingSubscriber;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.jayway.awaitility.Awaitility.await;

public class PublishingAndConsumingSubscriberTest
{
    @Test
    public void whenSubscribeToIt_thenShouldConsumeAll()
            throws InterruptedException {

        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        PublishingAndConsumingSubscriber<String> subscriber = new PublishingAndConsumingSubscriber<>();
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        // when
        assertThat(publisher.getNumberOfSubscribers(),is(1));
        items.forEach(publisher::submit);
        publisher.close();

        // then
        await().atMost(1000, TimeUnit.MILLISECONDS)
                .until(
                        () -> assertThat(subscriber.consumedElements.size(), is(items.size()))
                );
    }

}
