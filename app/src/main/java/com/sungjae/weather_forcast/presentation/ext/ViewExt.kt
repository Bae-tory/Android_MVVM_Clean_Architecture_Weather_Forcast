package com.sungjae.weather_forcast.presentation.ext

import android.view.View
import android.widget.TextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


typealias ViewClicked = (View) -> Unit

inline fun <reified T : Any> View.textReplacedViaRegex(oldValue: String, newValue: String = ""): T {
    return when (T::class) {
        Long::class -> try {
            (this as TextView).text.toString().replace(oldValue, newValue).toLong() as T
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            0L as T
        }

        String::class -> (this as TextView).text.toString().replace(oldValue, newValue) as T
        else -> throw IllegalStateException("not supported type")
    }
}

/**
 * @see
 */
class ThrottleFirstClickListener(
    private val listener: ViewClicked,
    INPUT_THROTTLE_DURATION: Long? = null
) : View.OnClickListener {

    private var disposable: Disposable? = null
    private val onClickPublishSubject = PublishSubject.create<View>()

    init {
        disposable =
            onClickPublishSubject
                .throttleFirst(
                    INPUT_THROTTLE_DURATION ?: DEFAULT_THROTTLE_DURATION,
                    TimeUnit.MILLISECONDS
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it.run(listener) }
    }

    override fun onClick(view: View) {
        onClickPublishSubject.onNext(view)
    }

    companion object {
        private const val DEFAULT_THROTTLE_DURATION = 200L
    }
}