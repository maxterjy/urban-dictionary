package minimalism.urbandict

interface FetchWordCallback {
    fun onComplete(words: ArrayList<Word>)
}