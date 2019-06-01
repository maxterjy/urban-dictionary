package minimalism.urbandict

import android.util.Log

interface FetchWordCallback {
    fun onComplete(words: ArrayList<Word>)
}