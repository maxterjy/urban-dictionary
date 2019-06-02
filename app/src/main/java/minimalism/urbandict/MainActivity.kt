package minimalism.urbandict

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(){

    val SEARCH_FRAGMENT_TAG = "search_fragment"

    var mSearchFragment: SearchFragment? = null
    lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp()
    }
}
