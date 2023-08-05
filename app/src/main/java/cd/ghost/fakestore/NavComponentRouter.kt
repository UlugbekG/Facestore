package cd.ghost.fakestore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavComponentRouter @Inject constructor() {

    private var currentActivity: FragmentActivity? = null

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
//            if (f is TabsFragment || f is NavHostFragment) return
//            val currentNavController = f.findNavController()
//            onNavControllerActivated(currentNavController)
//            destinationListeners.forEach { it() }
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
        }
    }

    fun onCreate(activity: FragmentActivity) {
        this.currentActivity = activity
        currentActivity
            ?.supportFragmentManager
            ?.registerFragmentLifecycleCallbacks(fragmentListener, true)
    }

    fun onDestroy() {
        currentActivity
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentListener)
        this.currentActivity = null
    }

    fun mainNavController(): NavController {
        val fragmentManager = currentActivity?.supportFragmentManager
        val navHost =
            fragmentManager?.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        return navHost.navController
    }

    fun tabsNavController(): NavController {
        val fragmentManager = currentActivity?.supportFragmentManager
        val navHost =
            fragmentManager?.findFragmentById(R.id.tabs_nav_host_container) as NavHostFragment
        return navHost.navController
    }

    fun popUp() {
        mainNavController().navigateUp()
//        currentActivity?.onBackPressedDispatcher?.onBackPressed()
    }

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putBundle(KEY_NAVIGATION, mainNavController().saveState())
    }

    fun onRestoreInstanceState(bundle: Bundle) {
        mainNavController().restoreState(bundle)
    }

    private companion object {
        const val KEY_NAVIGATION = "navigationState"
    }
}