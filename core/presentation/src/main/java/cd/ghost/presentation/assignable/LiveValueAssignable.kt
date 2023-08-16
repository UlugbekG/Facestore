package ua.cn.stu.multimodule.core.presentation.assignable

import cd.ghost.presentation.live.LiveValue
import cd.ghost.presentation.live.MutableLiveValue

internal class LiveValueAssignable<T>(
    private val liveValue: LiveValue<T>
) : Assignable<T> {

    override fun setValue(value: T) {
        (liveValue as? MutableLiveValue<T>)?.setValue(value)
    }
}