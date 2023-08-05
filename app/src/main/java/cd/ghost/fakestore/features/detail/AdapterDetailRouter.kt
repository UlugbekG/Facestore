package cd.ghost.fakestore.features.detail

import cd.ghost.catalog.presentation.detail.DetailRouter
import cd.ghost.fakestore.NavComponentRouter
import javax.inject.Inject

class AdapterDetailRouter @Inject constructor(
    private val navComponentRouter: NavComponentRouter
) : DetailRouter {

    override fun back() {
        navComponentRouter.popUp()
    }
}