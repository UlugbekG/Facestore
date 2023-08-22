package cd.ghost.fakestore.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.navigation.NavComponentRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navComponentRouter: NavComponentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        navComponentRouter.onCreate(this)

        if (savedInstanceState != null) {
            navComponentRouter.onRestoreInstanceState(savedInstanceState)
        }

    }

    override fun onNavigateUp(): Boolean {
        return navComponentRouter.navigateUp() || super.onNavigateUp()
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