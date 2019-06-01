package minimalism.urbandict

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), FetchWordCallback{

    override fun onComplete(words: ArrayList<Word>) {

        var builder = StringBuilder()

        for (i in 0..words.size-1) {
            builder.append(words[i])
        }

        tvHello.setText(builder.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvHello = findViewById<TextView>(R.id.tvHello)

        val task = FetchWordTask()
        task.mFetchCallback = this
        task.execute("hello")

    }
}
