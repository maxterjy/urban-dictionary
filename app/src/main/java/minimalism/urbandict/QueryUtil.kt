package minimalism.urbandict

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class QueryUtil {
    companion object {
        fun createURL(urlStr: String) : URL? {
            var url: URL? = null;

            try {
                url = URL(urlStr)
            }
            catch (e: MalformedURLException) {
                //TODO log
            }

            return url
        }

        fun sendGETRequest(url: URL?): String {
            var response = ""

            if (url == null)
                return response

            var connection: HttpURLConnection? = null
            var inputStream: InputStream? = null

            try {
                connection = url.openConnection() as HttpURLConnection
                connection.apply {
                    readTimeout = 10000
                    connectTimeout = 15000
                    requestMethod = "GET"
                    connect()
                }

                if (connection.responseCode == 200) {
                    inputStream = connection.inputStream
                    response = readFromStream(inputStream)
                }
                else {
                    //TODO warning log
                }
            }
            catch (e: IOException) {

            }
            finally {
                connection?.disconnect()
                inputStream?.close()
            }

            return response
        }

        fun readFromStream(inputStream: InputStream?): String {
            var builder = StringBuilder()

            inputStream?.let {
                var inputStreamReader = InputStreamReader(it)
                var reader = BufferedReader(inputStreamReader)

                var line = reader.readLine()

                while (line != null) {
                    builder.append(line)
                    line = reader.readLine()
                }
            }

            return builder.toString()
        }

        fun getWordsFromJson(jsonStr: String): ArrayList<Word> {
            var wordArray = ArrayList<Word>()

            try {
                val root = JSONObject(jsonStr)
                val arr = root.getJSONArray("list")


                for(i in 0..arr.length()) {
                    val obj = arr.getJSONObject(i)

                    val word = obj.getString("word")
                    val definition = obj.getString("definition")
                    val example = obj.getString("example")

                    val item = Word(word, definition, example)
                    wordArray.add(item)
                }
            }
            catch (ex: JSONException) {
                //TODO
            }

            return wordArray
        }
    }
}