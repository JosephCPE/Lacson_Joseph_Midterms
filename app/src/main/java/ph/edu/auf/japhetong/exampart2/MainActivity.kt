package ph.edu.auf.japhetong.exampart2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ph.edu.auf.japhetong.exampart2.adapters.NotesAdapter
import ph.edu.auf.japhetong.exampart2.dialog.AddNoteDialog
import ph.edu.auf.japhetong.exampart2.helpers.PrefsHelper
import ph.edu.auf.japhetong.exampart2.models.NoteModel

class MainActivity : AppCompatActivity() {

    private lateinit var notesAdapter: NotesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var btnOpenAbout: Button
    private lateinit var btnOpenWebsite: Button

    // Store all notes
    private val noteList = mutableListOf<NoteModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind UI elements
        recyclerView = findViewById(R.id.recyclerView)
        etSearch = findViewById(R.id.etSearch)
        fabAdd = findViewById(R.id.fabAdd)
        btnOpenAbout = findViewById(R.id.btnOpenAbout)
        btnOpenWebsite = findViewById(R.id.btnOpenWebsite)

        // Load notes from SharedPreferences
        noteList.addAll(PrefsHelper.loadNotes(this))

        // Setup RecyclerView with adapter
        notesAdapter = NotesAdapter(noteList.toMutableList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notesAdapter

        // Floating Action Button → Add new note
        fabAdd.setOnClickListener {
            val dialog = AddNoteDialog { note ->
                noteList.add(note)                     // add new note
                PrefsHelper.saveNotes(this, noteList)  // save updated list
                filterNotes(etSearch.text.toString())  // refresh list with filter
            }
            dialog.show(supportFragmentManager, "AddNoteDialog")
        }

        // Search filter using TextWatcher
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterNotes(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Explicit intent → Open AboutActivity
        btnOpenAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        // Implicit intent → Open a website
        btnOpenWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            startActivity(intent)
        }
    }

    // Function to filter notes based on search input
    private fun filterNotes(query: String) {
        val filtered = if (query.isEmpty()) {
            noteList
        } else {
            noteList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.content.contains(query, ignoreCase = true)
            }
        }
        notesAdapter.updateList(filtered)
    }
}
