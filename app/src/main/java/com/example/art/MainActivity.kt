package com.example.art

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

var curentItem: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        reloadImage()
        val next: Button = findViewById(R.id.nextImag)
        val before: Button = findViewById(R.id.beforeImage)
    }

    public fun openSettings(view:View) {
        val intent:Intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    public fun nextImage(view:View) {
        if(curentItem + 1 < GlobalList.size) {
            curentItem++
        } else {
            curentItem = 0
        }
        reloadImage()
    }

    public fun beforeImage(view:View) {
        if(curentItem - 1 >= 0) {
            curentItem--
        } else {
            curentItem = GlobalList.size - 1
        }
        reloadImage()
    }

    private fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun reloadImage() {
        val mainImage: ImageView = findViewById(R.id.imageMainView)
        if(GlobalList.size > 0) {
            Glide.with(this)
                .load(GlobalList[curentItem].image)
                .into(mainImage)
        }
        val mainText: TextView = findViewById(R.id.mainText)

        if(GlobalList.size >= 1){
            val description:String? = GlobalList[curentItem].description.toString()
            if (description != "" &&
                description != null) {
                mainText.text = description.toString()
            }
        }
    }
}