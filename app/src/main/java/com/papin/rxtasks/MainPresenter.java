package com.papin.rxtasks;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Pair;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import rx.observables.MathObservable;

class MainPresenter {

    private static final String TAG = "TAG";
    private Network network;


    public MainPresenter() {
        network = new Network();
    }

    @SuppressLint("CheckResult")
    void loadSmth() {


    }

    void task1() {

//        Observable<List<Story>> observable = Observable.concat(observer -> network.getFirstPage().concatWith(network.getSecondPage())
//                .toList()
//                .subscribe(t -> Log.d(TAG, "\n" + t.toString())));
//
//
//        observable.subscribe();
        network.getFirstPage()
                .subscribeOn(Schedulers.io())
                .zipWith(network.getSecondPage(), new BiFunction<List<Story>, List<Story>, List<Story>>() {
                    @NonNull
                    @Override
                    public List<Story> apply(@NonNull List<Story> stories, @NonNull List<Story> stories2) throws Exception {
                        stories.addAll(stories2);
                        return stories;
                    }
                })
                .subscribe(t -> Log.d(TAG, "\n" + t));

    }


    void task2() {

        network.getFirstPage()
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapIterable(stories -> stories)
                .flatMap(story -> network.getAuthor(story.getAuthor()).toObservable())
                .filter(author -> author.getKarma() > 3000)
                .toList()
                .subscribe(t -> Log.d(TAG, "\n" + t));

    }

    void task3() {

        network.onMaybeExceptionCreate()
                .subscribeOn(Schedulers.io())
                .subscribe(t -> Log.d(TAG, "\n" + t));

    }

    void task4() {
        network.onMaybeCreate()
                .subscribeOn(Schedulers.io())
                .subscribe(t -> Log.d(TAG, "\n" + t));
    }

    void task5() {
        network.onSingleCreate()
                .subscribeOn(Schedulers.io())
                .subscribe(t -> Log.d(TAG, "\n" + t));
    }

    void task7() {

        Observable
                .interval(1000L, TimeUnit.MILLISECONDS)
                .filter(x -> x > 0 && x <= 10)
                .scan((i1, i2) -> i1 + i2)
                .timeInterval()
                .flatMap(aLong -> network.getNumberPage(aLong).toObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> Log.d(TAG, "\n" + t));


    }

    void task8() {
        Executor executorFirst = Executors.newSingleThreadExecutor();
        Executor executorSecond = Executors.newSingleThreadExecutor();
        Executor executorThird = Executors.newSingleThreadExecutor();

        executorFirst.execute(new Runnable() {
            @Override
            public void run() {
                network.getFirstPage()
                        .subscribeOn(Schedulers.io());
                Log.d(TAG, "run: " + Thread.currentThread().getName());
            }
        });
        executorSecond.execute(new Runnable() {
            @Override
            public void run() {
                network.getSecondPage()
                        .subscribeOn(Schedulers.io());

                Log.d(TAG, "run: " + Thread.currentThread().getName());

            }
        });
        executorThird.execute(new Runnable() {
            @Override
            public void run() {
                network.getFirstPage()
                        .subscribe(t -> Log.d(TAG, "\n" + t));
                network.getSecondPage()
                        .subscribe(t -> Log.d(TAG, "\n" + t));
            }
        });
    }

    void task9() {
        Observable
                .interval(1000L, TimeUnit.MILLISECONDS)
                .filter(x -> x <= 10)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + aLong);
                        if (aLong == 7) {
                            throw new Exception("Your error message");
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable);
                    }
                })
                .subscribe(s -> Log.d(TAG, "Subscribed"));

    }


    void task10() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(t -> Log.d(TAG, "sub: " + t));
        subject.onNext(4);
        subject.onNext(5);
        subject.onNext(6);
        subject.onComplete();
    }

    void task11() {

        network.getFirstPage()
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map(stories -> stories.get(0))
                .flatMap(stories -> network.getAuthor(stories.getAuthor()).toObservable())

//                .map(author -> new CombineAuthor(author.getKarma(), author.getName(), new Story().getTitle()))


//                .toObservable()
//                .flatMapIterable(stories -> stories)
//                .flatMap(story -> network.getAuthor(story.getAuthor()).toObservable())
//
//                .map(author -> new CombineAuthor(author.getKarma(), author.getName(), ""))
//                .toList()
                .subscribe(t -> Log.d(TAG, "\n" + t));


    }

}
