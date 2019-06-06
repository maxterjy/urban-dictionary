package minimalism.urbandict


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController


class BookmarkFragment : Fragment(), BookmarkAdapter.BookmarkCallback, SwipeToDelete.SwipeToDeleteCallback {


    lateinit var mBookmarkDBHelper: BookmarkDBHelper
    lateinit var mRcvBookmark: RecyclerView
    lateinit var mBookmarkAdapter: BookmarkAdapter
    lateinit var mWords: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBookmarkDBHelper = BookmarkDBHelper(context)

        mWords = mBookmarkDBHelper.getAllItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var outView: View =  inflater.inflate(R.layout.fragment_bookmark, container, false)

        mRcvBookmark = outView.findViewById(R.id.recycler_view_bookmark)

        mBookmarkAdapter = BookmarkAdapter(mWords)
        mBookmarkAdapter.mBookmarkCallback = this

        mRcvBookmark.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mBookmarkAdapter

        }

        var swipeToDelete = SwipeToDelete()
        swipeToDelete.mCallback = this
        var itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(mRcvBookmark)

        return outView
    }

    override fun onItemClicked(word: String) {
        view?.findNavController()?.navigate(BookmarkFragmentDirections.actionBookmarkFragmentToResultFragment(word))
    }

    override fun onSwipe(index: Int) {
        Toast.makeText(context, "Removed item from bookmark", Toast.LENGTH_SHORT).show()

        val word = mWords[index]
        mBookmarkDBHelper.removeItem(word)
        mBookmarkAdapter.removeItem(index)
    }
}
