package minimalism.urbandict

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SwipeToDelete: ItemTouchHelper.SimpleCallback{

    interface SwipeToDeleteCallback {
        fun onSwipe(index: Int)
    }

    var mCallback: SwipeToDeleteCallback? = null

    constructor(): super(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, p1: Int) {
        mCallback?.onSwipe(holder.adapterPosition)
    }
}