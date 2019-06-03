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
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
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
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        showResultOnUI(words)
    }

    private fun showResultOnUI(words: ArrayList<Word>) {
        val wordAdapter = WordAdapter(words)
        mFragmentBinding.recyclerViewWords.adapter = wordAdapter
    }


    override fun onComplete(words: ArrayList<Word>) {
        finishSearch(words)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_result, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.add_bookmark) {
            Toast.makeText(context, "Added to bookmark", Toast.LENGTH_SHORT).show()

            return true
        }

        return false
    }
}
