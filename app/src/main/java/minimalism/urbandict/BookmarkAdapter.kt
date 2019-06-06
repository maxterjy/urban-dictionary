package minimalism.urbandict

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

class BookmarkAdapter(): RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    interface BookmarkCallback {
        fun onItemClicked(word: String)
    }

    lateinit var mWords: ArrayList<String>
    var mBookmarkCallback:BookmarkCallback? = null

    constructor(words: ArrayList<String>): this() {
        mWords = words
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(p0.context)
        var view = inflater.inflate(R.layout.bookmark_item_view, p0, false)

        return BookmarkAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        val word = mWords.get(index)
        holder.mTvWord.setText(word)

        holder.itemView.setOnClickListener({
            mBookmarkCallback?.onItemClicked(word)
        })
    }

    fun removeItem(index: Int) {
        mWords.removeAt(index)
        notifyItemRemoved(index)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var mTvWord: TextView

        init {
            mTvWord = itemView.findViewById(R.id.tv_word)
        }
    }
}