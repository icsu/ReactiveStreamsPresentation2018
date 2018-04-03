package rx.operators;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TimeIntervals
{
    int index;
    long time;

    TimeIntervals(int index, int time)
    {
        this.index = index;
        this.time = time;
    }
}

public class DebounceSample
{
    // TODO - not working
    public static void main(String[] args)
    {
        TimeIntervals[] intervals = new TimeIntervals[]{new TimeIntervals(0, 100),
                                    new TimeIntervals(1, 600),
                                    new TimeIntervals(2, 400),
                                    new TimeIntervals(3, 700),
                                    new TimeIntervals(4, 200),
                                    new TimeIntervals(5, 600)};

        Observable.fromArray(intervals)
                .flatMap(interval -> Observable.just(interval.index)
                .delay(interval.time, TimeUnit.MILLISECONDS))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

    }
}
