package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SetimaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setima_tela)
        val botao = findViewById<Button>(R.id.btn7)
        botao.setOnClickListener(this::telaOito)
    }

    fun telaOito (view: View) {
        val intent = Intent(this, OitavaTela::class.java)
        startActivity(intent)
    }
}