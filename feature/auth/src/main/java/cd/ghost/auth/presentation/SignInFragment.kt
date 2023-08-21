package cd.ghost.auth.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.auth.R
import cd.ghost.auth.databinding.FragmentSignInBinding
import cd.ghost.presentation.live.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding<FragmentSignInBinding>()
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignIn.setOnClickListener {
                val username = edUsername.text?.toString()
                val password = edPassword.text?.toString()
                viewModel.signIn(username, password)
            }
            viewModel.message.observeEvent(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}