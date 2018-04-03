package rx.hotcold;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

import java.util.Random;

class RandomClass
{
    int i = 0;
    RandomClass()
    {
        i = new Random().nextInt();
    }
}

class ConnectThread implements Runnable
{

    ConnectableObservable observable;
    Consumer consumer;

    int sleepTime;

    ConnectThread(ConnectableObservable observable, int sleepTime, Consumer consumer)
    {
        this.observable = observable;
        this.sleepTime = sleepTime;
        this.consumer = consumer;
    }

    @Override public void run()
    {
        try
        {
            Thread.sleep(sleepTime);

            observable.subscribe(consumer);

            if (sleepTime == 0) {
                observable.connect();
            }

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class HotObservable
{
    public static void main(String[] args)
    {
        Observable<RandomClass> cold = Observable.create(subscriber -> {
            for (int i = 0; i <= 5; i++)
            {
                RandomClass obj = new RandomClass();
                System.out.println("Source Emit " + obj.i);
                subscriber.onNext(obj);
                Thread.sleep(100);
            }
        });

        ConnectableObservable hot = cold.publish();


        Thread thread1 = new Thread(new ConnectThread(hot, 0, obj -> System.out.println("Subscriber 1: " + ((RandomClass)obj).i)));
        Thread thread2 = new Thread(new ConnectThread(hot, 300, obj -> System.out.println("Subscriber 2: " + ((RandomClass)obj).i)));

        thread2.start();
        thread1.start();

    }
}
