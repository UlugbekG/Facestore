package cd.ghost.fakestore.main.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import cd.ghost.fakestore.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val viewModel by viewModels<TabsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.navigationBarColor =
            SurfaceColors.SURFACE_2.getColor(requireContext())

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tabs_nav_host_container) as NavHostFragment

        val navController = navHostFragment.navController

        val bottomNavigation = view.findViewById(R.id.bottom_navigation) as BottomNavigationView

        NavigationUI.setupWithNavController(
            bottomNavigation,
            navController,
        )

        viewModel.cartItems.observe(viewLifecycleOwner) {
            if (it != null && it != 0) bottomNavigation.getOrCreateBadge(R.id.cart_nav_graph).number = it
        }
    }
}