package minimalism.urbandict


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import java.lang.StringBuilder


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ResultFragment : Fragment(), FetchWordCallback {

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
        var outView = inflater.inflate(R.layout.fragment_result, container, false)

        mTvResult = outView.findViewById<TextView>(R.id.tv_result)

        return outView
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

        view?.findNavController()?.navigate(R.id.action_searchFragment_to_resultFragment)
    }


    override fun onComplete(words: ArrayList<Word>) {
        finishSearch(words)
    }
}
