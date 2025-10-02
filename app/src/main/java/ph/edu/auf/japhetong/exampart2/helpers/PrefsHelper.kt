package ph.edu.auf.japhetong.exampart2.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ph.edu.auf.japhetong.exampart2.models.NoteModel

object PrefsHelper {
    private const val PREF_NAME = "notes_prefs"
    private const val KEY_NOTES = "notes_list"

    fun saveNotes(context: Context, notes: List<NoteModel>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(notes)
        editor.putString(KEY_NOTES, json)
        editor.apply()
    }

    fun loadNotes(context: Context): MutableList<NoteModel> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_NOTES, null)
        return if (json != null) {
            val type = object : TypeToken<List<NoteModel>>() {}.type
            Gson().fromJson<List<NoteModel>>(json, type).toMutableList()
        } else mutableListOf()
    }
}
