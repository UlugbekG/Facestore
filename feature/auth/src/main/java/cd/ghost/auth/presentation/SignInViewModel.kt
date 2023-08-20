package cd.ghost.auth.presentation

import cd.ghost.auth.domain.SignInUseCase
import cd.ghost.auth.domain.exceptions.EmptyPasswordException
import cd.ghost.auth.domain.exceptions.EmptyUsernameException
import cd.ghost.auth.domain.exceptions.IncorrectPasswordLengthException
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    fun signIn(username: String?, password: String?) {
        viewModelScope.launch {
            try {
                signInUseCase.signIn(username, password)
            } catch (e: EmptyUsernameException) {

            } catch (e: EmptyPasswordException) {

            } catch (e: IncorrectPasswordLengthException) {

            }
        }
    }
}