package rx.operators;

import io.reactivex.Flowable;

public class FlatMapBufferSample
{
    public static void main(String args[])
    {
        Flowable.range(1, 10)
                .buffer(2)
                .flatMap(list ->
                     Flowable.just(list.stream().mapToInt(i -> i.intValue()).sum()))
                .toList()
                .subscribe(System.out::println);
    }
}
