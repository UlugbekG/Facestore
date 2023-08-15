package cd.ghost.fakestore.main

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

interface NavControllerHolder {

    /**
     * Register back handler.
     * @param scope should be viewLifecycleScope or dialog lifecycle scope
     * @param handler callback which should return true if you want to cancel default back logic
     */
    fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean)

    fun rootNavController(): NavController

    fun requireActivity():FragmentActivity?

}