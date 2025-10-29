package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SextaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sexta_tela)
        val botao = findViewById<Button>(R.id.btn6)
        botao.setOnClickListener(this::telaSete)
    }

    fun telaSete (view: View) {
        val intent = Intent(this, SetimaTela::class.java)
        startActivity(intent)
    }
}