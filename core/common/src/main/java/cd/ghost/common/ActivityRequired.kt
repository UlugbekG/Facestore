package cd.ghost.common

import androidx.fragment.app.FragmentActivity

interface ActivityRequired {
    fun onCreate(activity: FragmentActivity)
    fun onStarted()
    fun onStopped()
    fun onDestroy()
}