package com.oschina.learn.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ztw on 16/5/6.
 */
public class RxSchedulers {

    public static final Observable.Transformer<?, ?> mTransformer = new Observable.Transformer<Observable, Observable>() {
        @Override
        public Observable<Observable> call(Observable<Observable> observableObservable) {
            return observableObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> io_main() {
        return (Observable.Transformer<T, T>) mTransformer;
    }
}
