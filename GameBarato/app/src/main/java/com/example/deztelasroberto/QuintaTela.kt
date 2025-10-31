package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class QuintaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quinta_tela)
        val botao = findViewById<Button>(R.id.btn5)
        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoMarcas = findViewById<Button>(R.id.btnMarcas)


        botao.setOnClickListener(this::telaSeis)
        botaoInicio.setOnClickListener(this::telaInicio)
        botaoMarcas.setOnClickListener(this::telaMarcas)
    }


    fun telaInicio (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun telaMarcas (view: View) {
        val intent = Intent(this, Marcas::class.java)
        startActivity(intent)
    }
    fun telaSeis (view: View) {
        val intent = Intent(this, SextaTela::class.java)
        startActivity(intent)
    }
}