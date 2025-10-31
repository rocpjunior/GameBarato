package com.example.deztelasroberto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val botaoMarcas = findViewById<Button>(R.id.btnMarcas)
        botaoMarcas.setOnClickListener(this::telaMarcas)

        val lista = listOf("Carro1", "Carro2", "Carro3", "Carro4")
        for(item in lista){
            val fragmento = caixinha()
            supportFragmentManager.beginTransaction()
                .add(R.id.orgaizador, fragmento)
                .commit()
        }
    }

    fun telaMarcas (view: View) {
        val intent = Intent(this, Marcas::class.java)
        startActivity(intent)
    }
}