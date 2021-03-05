package com.papin.rxtasks;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


class Network {

    private List<String> names = Arrays.asList("asdad", "AKLSJDNKASJD", "34o;da", "134asfj", "AJSFHIUQ@", "o32jrgadspif", "1ip3epasd", "jipshgf1", "3io14eja", "0ijhb", "cfghui", "DDDD", "Dima", "Masha", "Vasya", "Petya", "Misha", "Katya", "Petr", "Dmytro", "Roma", "Sonya", "Pasha", "Ilya", "Nastya");
    private List<String> story = Arrays.asList("Dima_1", "Masha_1", "Vasya_1", "Petya_1", "Misha_1", "Katya_1", "Petr_1", "Dmytro_1", "Roma_1", "Sonya_1", "Pasha_1", "Ilya_1", "Nastya_1");

    Single<List<Story>> getFirstPage() {
        List<Story> stories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Author author = new Author(new Random().nextInt(7000), names.get(new Random().nextInt(names.size())));
            stories.add(new Story(i, story.get(new Random().nextInt(story.size())), author.getName()));
        }
        return Single.just(stories);
    }

    Single<List<Story>> getSecondPage() {
        List<Story> stories = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            Author author = new Author(new Random().nextInt(8000), names.get(new Random().nextInt(names.size())));
            stories.add(new Story(i, story.get(new Random().nextInt(story.size())), author.getName()));
        }
        return Single.just(stories);
    }

    Single<Author> getAuthor(String name) {
        return Single.just(new Author(new Random().nextInt(7000), name));
    }

    public Single<Boolean> loadSmth() {
        return Single.just(true);
    }


    public Maybe<String> onMaybeExceptionCreate() {

        Random random = new Random();

        return Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Exception {
                if (random.nextBoolean()) {
                    emitter.onSuccess("Bang!");
                } else {
                    throw new IllegalArgumentException();
                }

            }
        });
    }

    public Maybe<String> onMaybeCreate() {
        Random random = new Random();

        return Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull MaybeEmitter<String> emitter) throws Exception {
                if (random.nextBoolean()) {
                    emitter.onSuccess("Bang!");
                } else {
                    emitter.onComplete();
                }

            }
        });

    }

    public Single<String> onMaybeSingleCreate() {
        Random random = new Random();

        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Exception {
                if (random.nextBoolean()) {
                    emitter.onSuccess("Bang!");
                } else {
                    emitter.onError(new Throwable("You're live"));
                }

            }
        });

    }

}
//t -> Log.d("TAG", "subscribeActual: " + t)