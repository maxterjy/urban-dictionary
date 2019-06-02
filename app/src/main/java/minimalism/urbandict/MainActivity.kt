package minimalism.urbandict

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(){

    val SEARCH_FRAGMENT_TAG = "search_fragment"

    var mSearchFragment: SearchFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mSearchFragment = supportFragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG) as SearchFragment?
//
//        if (mSearchFragment == null) {
//            mSearchFragment = SearchFragment()
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fragment_container, mSearchFragment!!, SEARCH_FRAGMENT_TAG)
//            transaction.commit()
//        }
    }
}
