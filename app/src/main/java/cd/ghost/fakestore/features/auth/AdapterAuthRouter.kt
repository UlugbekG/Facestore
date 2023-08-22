package cd.ghost.fakestore.features.auth

import androidx.navigation.navOptions
import cd.ghost.auth.AuthRouter
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.navigation.NavControllerHolder
import javax.inject.Inject

class AdapterAuthRouter @Inject constructor(
    private val navControllerHolder: NavControllerHolder
) : AuthRouter {

    override fun navigateToTabs() {
        navControllerHolder.getRootNavController()?.navigate(
            resId = R.id.action_signInFragment_to_tabsFrag,
            args = null,
            navOptions = navOptions {
                popUpTo(R.id.signInFragment) {
                    inclusive = true
                }
            }
        )
    }

}