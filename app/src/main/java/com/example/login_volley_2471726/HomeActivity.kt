package com.example.login_volley_2471726

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val btn: Button = findViewById(R.id.buttoningresar)
        btn.setOnClickListener {
            val intent: Intent = Intent(this,loginActivity::class.java)
            startActivity(intent)

        }
    }
}