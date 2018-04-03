package rx.hotcold;

import io.reactivex.Observable;

public class ColdObservable
{
    public static void main(String[] args)
    {
        Observable<Integer> cold = Observable.create(subscriber -> {
                    for (int i = 0; i <= 1; i++)
                    {
                        System.out.println("Source Emit " + i);
                        subscriber.onNext(i);
                    }
                }
        );

        cold.subscribe(i -> System.out.println("Subscriber 1: " + i));
        cold.subscribe(i -> System.out.println("Subscriber 2: " + i));

    }
}
