package cd.ghost.common

interface ActivityRequired {
    fun onCreate()

    fun onStart()

    fun onStop()

    fun onDestroy()

}