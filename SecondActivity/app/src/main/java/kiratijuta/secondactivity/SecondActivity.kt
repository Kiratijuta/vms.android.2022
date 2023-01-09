package kiratijuta.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val displayName = intent.getStringExtra("NAME")
        displayTextView.text = "Hello $displayName 🎉"

        saveButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("AGE", ageInput.text.toString())
            setResult(Activity.RESULT_OK, intent)
        }
    }
}