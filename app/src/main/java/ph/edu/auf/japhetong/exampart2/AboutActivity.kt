package ph.edu.auf.japhetong.exampart2

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instead of XML, we directly create a TextView in code
        val textView = TextView(this).apply {
            text = "This is the About screen (Explicit Intent Example)"
            textSize = 18f
            gravity = Gravity.CENTER
        }

        // Set the TextView as the whole screen content
        setContentView(textView)
    }
}
