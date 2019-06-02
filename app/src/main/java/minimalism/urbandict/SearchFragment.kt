package minimalism.urbandict


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import org.w3c.dom.Text
import java.lang.StringBuilder


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var outView = inflater.inflate(R.layout.fragment_search, container, false)

        val edtKeyword = outView.findViewById<EditText>(R.id.edt_keyword)
        val btnSearch = outView.findViewById<Button>(R.id.btn_search)

        btnSearch.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val key = edtKeyword.text.toString()
                goToSearchFragment(key)
            }
        })

        return outView
    }

    fun goToSearchFragment(key: String) {
        view?.findNavController()?.navigate(R.id.action_searchFragment_to_resultFragment)
    }
}
