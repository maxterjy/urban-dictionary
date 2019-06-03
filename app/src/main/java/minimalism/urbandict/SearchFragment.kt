package minimalism.urbandict


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        edtKeyword.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if ((actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) && event?.action == KeyEvent.ACTION_DOWN) {
                    val key = edtKeyword.text.toString()
                    goToResultFragment(key)
                    return true
                }

                return false
            }
        })

        val btnSearch = outView.findViewById<Button>(R.id.btn_search)
        btnSearch.setOnClickListener {v ->
            edtKeyword.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }

        return outView
    }

    fun goToResultFragment(key: String) {
        view?.findNavController()?.navigate(SearchFragmentDirections.actionSearchFragmentToResultFragment(key))
    }
}
