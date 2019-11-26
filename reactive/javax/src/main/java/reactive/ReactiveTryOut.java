package reactive;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Hello world!
 */
public final class ReactiveTryOut {

    public static void main(String[] args) throws InterruptedException {
        final Flowable<Integer> flow = Flowable.range(0, 100).map(v -> v * v).filter(v -> v % 3 == 0);
        flow.subscribe(System.out::println);

        final Observable<Integer> observable = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onNext(4);

            s.onError(new RuntimeException("Throwing error ...."));
        });

        observable.map(i -> "Number " + i)
                .onErrorReturn(e -> {
                    System.out.println("Error");
                    System.out.println("Thread is: " + Thread.currentThread().getName());
                    return "new value";
                })
                .subscribe(System.out::println);

        final Flowable<String> flowException = Flowable.<String>create(e -> {
            e.onNext("Running");
            e.onError(new RuntimeException("Throwing error from flow ...."));
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());

        flowException.subscribe(System.out::println, ex -> {
            System.out.println("Exception have been thrown");
            ex.printStackTrace();
        });

        final Flowable<String> async = Flowable.<String>fromCallable(() -> {
            Thread.sleep(1000); // imitate expensive computation

            System.out.println("Thread is: " + Thread.currentThread().getName());

            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());

        async.subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000);
    }
}
