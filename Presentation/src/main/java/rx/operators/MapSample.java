package rx.operators;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class MapSample
{
    public static void main(String args[])
    {
        List<String> words = Arrays.asList("Ana", "are", "mere");

        Observable.fromIterable(words)
                .map(s -> s.length())
                .subscribe(System.out::println);
    }

}
