package ph.edu.auf.japhetong.exampart2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.edu.auf.japhetong.exampart2.R
import ph.edu.auf.japhetong.exampart2.models.NoteModel

class NotesAdapter(
    private var notes: List<NoteModel>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvContent.text = note.content
    }


    override fun getItemCount(): Int = notes.size

    // Update adapter with a new filtered list
    fun updateList(newNotes: List<NoteModel>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    // Add a new note (extra function like in original)
    fun addNewItem(note: NoteModel) {
        if (notes is MutableList) {
            (notes as MutableList).add(note)
            notifyItemInserted(notes.size - 1)
        } else {
            // fallback: recreate list if immutable
            val mutable = notes.toMutableList()
            mutable.add(note)
            notes = mutable
            notifyDataSetChanged()
        }
    }
}
