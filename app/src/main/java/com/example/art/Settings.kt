package com.example.art

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val images = PaintInformation()
        val imageView: ImageView = findViewById(R.id.imageView)
        val description:EditText = findViewById(R.id.discriptionImage)
        val  url:EditText = findViewById(R.id.urlAdresImage)
        val saveButton:Button = findViewById(R.id.saveSettings)
        saveButton.setOnClickListener {
            images.addPaintItem(description.text.toString(), url.text.toString())
            Glide.with(this)
                .load(url.text.toString())
                .into(imageView)
            description.text = null
            url.text = null
            Toast.makeText(this, "Описание добавленно успешно", Toast.LENGTH_SHORT).show()
        }
    }


    public fun openSettings(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}