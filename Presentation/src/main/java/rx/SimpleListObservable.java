package rx;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleListObservable
{
    public static void main(String args[])
    {
        List<String> words = Arrays.asList("Ana", "are", "mere");

        Observable.fromIterable(words).subscribe(System.out::println);
    }

}
