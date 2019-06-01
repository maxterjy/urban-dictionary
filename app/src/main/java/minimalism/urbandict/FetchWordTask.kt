package minimalism.urbandict

import android.os.AsyncTask
import android.util.Log
import java.net.URL

class FetchWordTask(): AsyncTask<String, Void, ArrayList<Word>>() {

    var mFetchCallback: FetchWordCallback? = null

    constructor(callback: FetchWordCallback):this() {
        mFetchCallback = callback
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): ArrayList<Word> {
        val key = params[0]
        val urlStr ="http://api.urbandictionary.com/v0/define?term=$key"

        val url = QueryUtil.createURL(urlStr!!)
        val response = QueryUtil.sendGETRequest(url)
        val words = QueryUtil.getWordsFromJson(response)

        return words
    }

    override fun onPostExecute(words: ArrayList<Word>) {
        super.onPostExecute(words)

        mFetchCallback?.onComplete(words)
    }


}