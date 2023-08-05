package cd.ghost.fakestore

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navComponentRouter: NavComponentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navComponentRouter.onCreate(this)

        if (savedInstanceState != null) {
            navComponentRouter.onRestoreInstanceState(savedInstanceState)
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        navComponentRouter.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        navComponentRouter.onDestroy()
    }

}