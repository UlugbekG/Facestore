package cd.ghost.fakestore.main.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import cd.ghost.fakestore.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabsFrag : Fragment(R.layout.frag_tabs) {

    private val viewModel by viewModels<TabsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tabs_nav_host_container) as NavHostFragment

        val navController = navHostFragment.navController

        val bottomNavigation = view.findViewById(R.id.bottom_navigation) as BottomNavigationView

        NavigationUI.setupWithNavController(
            bottomNavigation,
            navController,
        )

        viewModel.orders.observe(viewLifecycleOwner) {
            if (it != 0) bottomNavigation.getOrCreateBadge(R.id.cartFrag2).number = it
        }
    }
}