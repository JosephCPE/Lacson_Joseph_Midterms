package ph.edu.auf.japhetong.exampart2.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ph.edu.auf.japhetong.exampart2.R
import ph.edu.auf.japhetong.exampart2.models.NoteModel

class AddNoteDialog(
    private val onNoteAdded: (NoteModel) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_note, null)

        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etContent = view.findViewById<EditText>(R.id.etContent)

        return AlertDialog.Builder(requireContext())
            .setTitle("Add Note")
            .setView(view)
            .setPositiveButton("Add") { _, _ ->
                val title = etTitle.text.toString().trim()
                val content = etContent.text.toString().trim()
                if (title.isNotEmpty() || content.isNotEmpty()) {
                    onNoteAdded(NoteModel(title, content))
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
