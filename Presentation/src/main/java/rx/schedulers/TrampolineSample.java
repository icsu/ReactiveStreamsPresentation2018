package rx.schedulers;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TrampolineSample
{
    public static void main(String[] args) throws InterruptedException
    {
        Consumer<Integer> onNext = i -> System.out.println("Number = " + i);

        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + "Start");

            worker.schedule(() -> {
                System.out.println("_middleStart");
                worker.schedule(() -> System.out.println("_worker_"));
                System.out.println("_middleEnd");
            });

            System.out.println("_mainEnd");
        }); Thread.sleep(500);
    }
}
