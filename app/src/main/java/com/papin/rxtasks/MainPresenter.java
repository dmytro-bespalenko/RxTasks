package com.papin.rxtasks;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

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


        Observable<List<Story>> observable = Observable.concat(observer -> network.getFirstPage().concatWith(network.getSecondPage())
                .toList()
                .subscribe(t -> Log.d(TAG, "\n" + t.toString())));
        observable.subscribe();

    }


    void task2() {
        network.getFirstPage()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Story>, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(@NonNull List<Story> stories) throws Exception {


                        return null;

                    }
                });

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
        network.onMaybeSingleCreate()
                .subscribeOn(Schedulers.io())
                .subscribe(t -> Log.d(TAG, "\n" + t));
    }

    void task7() {
//        Observable.interval(1000L, TimeUnit.MILLISECONDS)
//                .map(x -> x + 1) // to start from 1 instead of 0
//                .map(x -> x + 2)
//                .take(10)
//                .subscribe(t -> Log.d(TAG, "\n" + t));


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


}
