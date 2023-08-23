package cd.ghost.fakestore.main.navigation

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
import androidx.navigation.fragment.findNavController
import cd.ghost.cart.presentation.cartlist.CartFrag
import cd.ghost.catalog.presentation.productlist.ProductsFragment
import cd.ghost.fakestore.R
import cd.ghost.profile.presentation.ProfileFragment
import cd.ghost.source.settings.AppSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class NavComponentRouter(private val appSettings: AppSettings) : NavControllerHolder {

    private val TAG = "NavComponentRouter"

    private var navController: NavController? = null
    private var homeNavController: NavController? = null
    private var cartNavController: NavController? = null
    private var profileNavController: NavController? = null
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
            // you can find any nav controller from this callback from fragment
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is ProductsFragment && homeNavController == null)
                homeNavController = f.findNavController()
            if (f is CartFrag && cartNavController == null)
                cartNavController = f.findNavController()
            if (f is ProfileFragment && profileNavController == null)
                profileNavController = f.findNavController()
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

    private val destinationListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            Log.d(TAG, "Destination: ${destination.label}")
        }

    fun onCreate(activity: FragmentActivity) {
        this.activity = activity

        val navController = findRootNavController()
        prepareRootGraph(navController)
        onNavControllerActivated(navController)

        activity
            .supportFragmentManager
            .registerFragmentLifecycleCallbacks(fragmentListener, true)

        setupBackHandlers()
    }

    fun onDestroy() {
        activity
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController?.removeOnDestinationChangedListener(destinationListener)

        clear()
    }

    fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
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

    fun restartApp() {
        appSettings.setCurrentToken(null)
        val navController = findRootNavController()
        prepareRootGraph(navController)
        onNavControllerActivated(navController)
    }

    fun navigateUp(): Boolean {
        return navController?.navigateUp() ?: false
    }

    private fun findRootNavController(): NavController {
        val fragmentManager = activity?.supportFragmentManager
        val navHost =
            fragmentManager?.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        return navHost.navController
    }

    private fun prepareRootGraph(navController: NavController) {
        val graph = navController.navInflater.inflate(R.navigation.main_graph)
        graph.setStartDestination(if (isSignedIn()) R.id.tabsFrag else R.id.signInFragment)
        navController.graph = graph
    }

    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
    }

    private fun isSignedIn(): Boolean = appSettings.getCurrentToken() != null

    override fun getRootNavController(): NavController? = navController

    override fun getHomeNavController(): NavController? = homeNavController

    override fun getCartsNavController(): NavController? = cartNavController

    override fun getProfileNavController(): NavController? = profileNavController

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putBundle(KEY_NAVIGATION, navController?.saveState())
    }

    fun onRestoreInstanceState(bundle: Bundle) {
        navController?.restoreState(bundle)
    }

    private fun clear() {
        navController = null
        homeNavController = null
        cartNavController = null
        profileNavController = null
        this.activity = null
    }

    private companion object {
        const val KEY_NAVIGATION = "navigationState"
    }
}

