package cd.ghost.fakestore.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import cd.ghost.fakestore.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine


class NavComponentRouter : NavControllerHolder {

    private val TAG = "NavComponentRouter"

    private var activity: FragmentActivity? = null
    private val onBackPressHandlers = LinkedHashSet<() -> Boolean>()
    private var fragmentDialogs: Int = 0

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            Log.d(TAG, "onFragmentViewCreated: $f")
            // you can find any nav controller from this callback from fragment
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)
            if (f is DialogFragment) fragmentDialogs++
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
            if (f is DialogFragment) fragmentDialogs--
        }
    }

    fun onCreate(activity: FragmentActivity) {
        this.activity = activity
        activity
            .supportFragmentManager
            .registerFragmentLifecycleCallbacks(fragmentListener, true)
        setupBackHandlers()
    }

    fun onDestroy() {
        activity
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentListener)
        this.activity = null
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        scope.launch {
            suspendCancellableCoroutine { continuation ->
                onBackPressHandlers.add(handler)
                continuation.invokeOnCancellation {
                    onBackPressHandlers.remove(handler)
                }
            }
        }
    }

    private fun setupBackHandlers() {
        activity
            ?.onBackPressedDispatcher
            ?.addCallback(
                activity!!,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (fragmentDialogs > 0) {
                            processAsUsual()
                            return
                        }
                        onBackPressHandlers.reversed().forEach { handler ->
                            if (handler.invoke()) {
                                return
                            }
                        }
                        processAsUsual()
                    }

                    private fun processAsUsual() {
                        isEnabled = false
                        activity?.onBackPressedDispatcher?.onBackPressed()
                        isEnabled = true
                    }
                }
            )
    }

    override fun rootNavController(): NavController {
        val fragmentManager = activity?.supportFragmentManager
        val navHost =
            fragmentManager?.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        return navHost.navController
    }

    override fun requireActivity(): FragmentActivity? {
        return activity
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