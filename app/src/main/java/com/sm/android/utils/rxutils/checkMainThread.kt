@file:JvmName("Preconditions")

package com.sm.android.utils.rxutils

import android.os.Looper
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

@RestrictTo(LIBRARY_GROUP)
fun checkMainThread(observer: Observer<*>): Boolean {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        observer.onSubscribe(Disposable.empty())
        observer.onError(IllegalStateException(
            "Expected to be called on the main thread but was " + Thread.currentThread().name))
        return false
    }
    return true
}