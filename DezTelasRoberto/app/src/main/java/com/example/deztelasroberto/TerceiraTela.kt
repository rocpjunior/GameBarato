package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TerceiraTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terceira_tela)
        val botao = findViewById<Button>(R.id.btn3)
        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoMarcas = findViewById<Button>(R.id.btnMarcas)


        botao.setOnClickListener(this::telaQuatro)
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
    fun telaQuatro (view: View) {
        val intent = Intent(this, QuartaTela::class.java)
        startActivity(intent)
    }
}