package minimalism.urbandict

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class WordAdapter(var dataset: ArrayList<Word>): RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    class ViewHolder(var view: View): RecyclerView.ViewHolder(view){
        lateinit var tvDefinition: TextView
        lateinit var tvExample: TextView

        init {
            tvDefinition = view.findViewById(R.id.tv_definition)
            tvExample = view.findViewById(R.id.tv_example)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var inflater = LayoutInflater.from(p0.context)
        var view = inflater.inflate(R.layout.word_item_view, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        holder.tvDefinition.setText(dataset.get(index)?.definition)
        holder.tvExample.setText(dataset.get(index)?.example)

    }



}