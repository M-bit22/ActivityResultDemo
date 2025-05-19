package com.example.activityresultdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var resultTextView: TextView
    
    // Using the new ActivityResult API (recommended over deprecated startActivityForResult)
    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val returnedText = data?.getStringExtra("INPUT_TEXT")
            
            // Update the UI with the returned text
            resultTextView.text = "Result: $returnedText"
            Toast.makeText(this, "Received: $returnedText", Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Operation canceled or input was invalid", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        resultTextView = findViewById(R.id.resultTextView)
        val startButton = findViewById<Button>(R.id.startActivityButton)
        
        startButton.setOnClickListener {
            // Create an intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            // Launch the activity with our ActivityResultLauncher
            getContent.launch(intent)
        }
    }
    
    // Legacy method (provided for reference, but we're using the newer approach above)
    // This is deprecated but included to match the assignment requirements
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // This method is deprecated but included for reference
        // Modern approach uses the ActivityResultLauncher above
    }
}
