package minimalism.urbandict

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(){

    val SEARCH_FRAGMENT_TAG = "search_fragment"

    var mSearchFragment: SearchFragment? = null
    lateinit var mNavController: NavController
    lateinit var mBottombar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController)

        mBottombar = findViewById(R.id.bottom_bar)
        mNavController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.resultFragment) {
                mBottombar.visibility = View.GONE
            }
            else {
                mBottombar.visibility = View.VISIBLE
            }
        }
        NavigationUI.setupWithNavController(mBottombar, mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        return NavigationUI.onNavDestinationSelected(item!!, mNavController)
    }
}
