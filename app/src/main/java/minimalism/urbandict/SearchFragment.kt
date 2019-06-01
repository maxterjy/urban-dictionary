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
import org.w3c.dom.Text
import java.lang.StringBuilder


class SearchFragment : Fragment(), FetchWordCallback {

    lateinit var mTvResult: TextView
    var mIsSearching = false
    lateinit var mFetchTask: FetchWordTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var outView = inflater.inflate(R.layout.fragment_search, container, false)

        val edtKeyword = outView.findViewById<EditText>(R.id.edt_keyword)
        val btnSearch = outView.findViewById<Button>(R.id.btn_search)
        mTvResult = outView.findViewById<TextView>(R.id.tv_result)

        btnSearch.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val key = edtKeyword.text.toString()
                startSearch(key)
            }
        })

        return outView;
    }

    fun startSearch(key: String){
        if (mIsSearching) {
            cancelSearch()
        }

        mFetchTask = FetchWordTask(this)
        mFetchTask.execute(key)
    }

    fun cancelSearch() {
        mIsSearching = false
        mFetchTask.cancel(true)
    }

    fun finishSearch(words: ArrayList<Word>){
        mIsSearching = false

        var builder = StringBuilder()
        for(i in 0..words.size-1) {
            builder.apply {
                append(words[i].definition + "\n")
                append(words[i].example + "\n")

                append("\n\n")
            }
        }

        mTvResult.setText(builder.toString())
    }


    override fun onComplete(words: ArrayList<Word>) {
        finishSearch(words)
    }
}
