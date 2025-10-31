package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Marcas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_marcas)
        val botao = findViewById<Button>(R.id.btn2)
        val botaoInicio = findViewById<Button>(R.id.btnInicio)


        botao.setOnClickListener(this::telaTres)
        botaoInicio.setOnClickListener(this::telaInicio)
    }

    fun telaInicio (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun telaTres (view: View) {
        val intent = Intent(this, TerceiraTela::class.java)
        startActivity(intent)
    }
}