package cd.ghost.fakestore.features.cart

import cd.ghost.cart.presentation.CartRouter
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.navigation.NavControllerHolder
import javax.inject.Inject


class AdapterCartRouter @Inject constructor(
    private val navControllerHolder: NavControllerHolder
) : CartRouter {

    override fun navigateToOrderScreen() {
        navControllerHolder.getCartsNavController()
            ?.navigate(R.id.action_cartFrag_to_createOrderFragment)
    }

}