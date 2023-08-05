package cd.ghost.fakestore.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import cd.ghost.fakestore.R


class NavComponentRouter : NavControllerHolder {

    private var activity: FragmentActivity? = null

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
        }
    }

    fun onCreate(activity: FragmentActivity) {
        this.activity = activity
        activity
            .supportFragmentManager
            .registerFragmentLifecycleCallbacks(fragmentListener, true)
    }

    fun onDestroy() {
        activity
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentListener)
        this.activity = null
    }

    override fun rootNavController(): NavController {
        val fragmentManager = activity?.supportFragmentManager
        val navHost =
            fragmentManager?.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        return navHost.navController
    }

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putBundle(KEY_NAVIGATION, rootNavController().saveState())
    }

    fun onRestoreInstanceState(bundle: Bundle) {
        rootNavController().restoreState(bundle)
    }

    private companion object {
        const val KEY_NAVIGATION = "navigationState"
    }
}