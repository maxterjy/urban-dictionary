package minimalism.urbandict


import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.inflate
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import minimalism.urbandict.databinding.FragmentResultBinding
import java.lang.StringBuilder


private const val ARG_KEYWORD = "keyword"


class ResultFragment : Fragment(), FetchWordCallback {


    var mIsSearching = false
    lateinit var mFetchTask: FetchWordTask
    lateinit var mArgs: ResultFragmentArgs
    lateinit var mFragmentBinding: FragmentResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mArgs = ResultFragmentArgs.fromBundle(arguments!!)

        (activity as AppCompatActivity).supportActionBar?.setTitle(mArgs.keyword)

        startSearch(mArgs.keyword)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var outView = inflater.inflate(R.layout.fragment_result, container, false)
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        mFragmentBinding.progressbarFetch.visibility = View.VISIBLE

        val viewManager = LinearLayoutManager(context)

        mFragmentBinding.recyclerViewWords.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }


        return mFragmentBinding.root
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
        mFragmentBinding.progressbarFetch.visibility = View.INVISIBLE
        mFetchTask.cancel(true)
    }

    fun finishSearch(words: ArrayList<Word>){
        mIsSearching = false
        mFragmentBinding.progressbarFetch.visibility = View.INVISIBLE
//        mFragmentBinding.tvKeyword.setText(mArgs.keyword)

        showResultOnUI(words)

//        var builder = StringBuilder()
//        for(i in 0..words.size-1) {
//            builder.apply {
//                append(words[i].definition)
//                append("\n")
//                append(words[i].example)
//
//                append("\n\n")
//            }
//        }
//
//        mFragmentBinding.tvResult.setText(builder.toString())
    }

    private fun showResultOnUI(words: ArrayList<Word>) {
        val wordAdapter = WordAdapter(words)
        mFragmentBinding.recyclerViewWords.adapter = wordAdapter
    }


    override fun onComplete(words: ArrayList<Word>) {
        finishSearch(words)
    }
}
