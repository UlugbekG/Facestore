package cd.ghost.fakestore.main.navigation

import cd.ghost.common.AppRestarter

class DefAppRestarter(
    private val navComponentRouter: NavComponentRouter
) : AppRestarter {
    override fun restartApp() {
        navComponentRouter.restartApp()
    }
}