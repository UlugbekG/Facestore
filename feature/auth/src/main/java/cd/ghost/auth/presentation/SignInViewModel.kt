package cd.ghost.auth.presentation

import cd.ghost.auth.domain.SignInUseCase
import cd.ghost.auth.domain.exceptions.EmptyPasswordException
import cd.ghost.auth.domain.exceptions.EmptyUsernameException
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    val message = liveEvent<String?>()

    fun signIn(username: String?, password: String?) = debounce {
        viewModelScope.launch {
            try {
                signInUseCase.signIn(username, password)
                message.publish("Successfully")
            } catch (e: EmptyUsernameException) {
                message.publish(e.message)
            } catch (e: EmptyPasswordException) {
                message.publish(e.message)
            } catch (e: Exception) {
                message.publish(e.message)
            }
        }
    }
}