package com.carbit3333333.giphyapp.widjet

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent <T>: MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) { Log.w("SingleLiveEvent", "Multiple observers registered but only one will be notified of changes.") }
        super.observe(owner, Observer<T> { t->
            if (pending.compareAndSet(true, false)){
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
    //    Используется в случаях, когда T равно Void, чтобы сделать звонки более чистыми.
    @MainThread
    fun call(){
        value = null
    }
}