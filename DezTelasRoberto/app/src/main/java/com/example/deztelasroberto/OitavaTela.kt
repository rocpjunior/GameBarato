package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OitavaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_oitava_tela)
        val botao = findViewById<Button>(R.id.btn8)
        botao.setOnClickListener(this::telaNove)
    }

    fun telaNove (view: View) {
        val intent = Intent(this, NonaTela::class.java)
        startActivity(intent)
    }
}