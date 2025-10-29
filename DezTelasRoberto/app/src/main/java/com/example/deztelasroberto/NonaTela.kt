package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NonaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nona_tela)
        val botao = findViewById<Button>(R.id.btn9)
        botao.setOnClickListener(this::telaDez)
    }

    fun telaDez (view: View) {
        val intent = Intent(this, DecimaTela::class.java)
        startActivity(intent)
    }
}