package rx;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class ResourceSubscriberSample
{
    public static void main(String[] args)
    {
        ResourceSubscriber<Integer> subscriber = new ResourceSubscriber<>()
        {
            @Override public void onStart()
            {
                request(Long.MAX_VALUE);
            }

            @Override public void onNext(Integer t)
            {
                System.out.println(t);
            }

            @Override public void onError(Throwable t)
            {
                t.printStackTrace();
            }

            @Override public void onComplete()
            {
                System.out.println("Done");
            }
        };

        Flowable.range(1, 10).subscribe(subscriber);

        subscriber.dispose();
    }
}
