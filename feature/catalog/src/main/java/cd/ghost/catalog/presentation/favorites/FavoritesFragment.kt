package cd.ghost.catalog.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding by viewBinding()
    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}