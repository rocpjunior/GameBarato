package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DecimaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_decima_tela)
        val botao = findViewById<Button>(R.id.btn10)
        botao.setOnClickListener(this::telaUm)
    }

    fun telaUm (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}