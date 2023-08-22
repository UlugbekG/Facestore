package cd.ghost.auth.presentation

import android.util.Log
import cd.ghost.auth.AuthRouter
import cd.ghost.auth.domain.SignInUseCase
import cd.ghost.auth.domain.exceptions.EmptyPasswordException
import cd.ghost.auth.domain.exceptions.EmptyUsernameException
import cd.ghost.common.ErrorResponseException
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val authRouter: AuthRouter
) : BaseViewModel() {

    val message = liveEvent<String?>()

    fun signIn(username: String?, password: String?) = debounce {
        viewModelScope.launch {
            try {
                signInUseCase.signIn(username, password)
                authRouter.navigateToTabs()
            } catch (e: EmptyUsernameException) {
                message.publish(e.message)
            } catch (e: EmptyPasswordException) {
                message.publish(e.message)
            } catch (e: ErrorResponseException) {
                message.publish(e.message)
            }
        }
    }
}