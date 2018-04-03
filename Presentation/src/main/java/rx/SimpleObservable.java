package rx;

import io.reactivex.Observable;

public class SimpleObservable
{
    public static void main(String args[])
    {
        Observable.just("Hello World").subscribe(System.out::println);
    }
}
