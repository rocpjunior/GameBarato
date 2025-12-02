package com.example.gamebarato.ux

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.Pesquisar
import com.example.gamebarato.R
import com.example.gamebarato.ux.favoritos.Favoritos
import com.example.gamebarato.ux.jogo.MainActivity
import com.example.gamebarato.ux.ofertas.Ofertas

class Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)

        botaoInicio.setOnClickListener(this::telaInicio)
        botaoPesquisar.setOnClickListener(this::telaPesquisar)
        botaoOfertas.setOnClickListener(this::telaOfertas)
        botaoFavoritos.setOnClickListener(this::telaFavoritos)

    }

    fun telaInicio (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun telaPesquisar (view: View) {
        val intent = Intent(this, Pesquisar::class.java)
        startActivity(intent)
    }
    fun telaOfertas (view: View) {
        val intent = Intent(this, Ofertas::class.java)
        startActivity(intent)
    }
    fun telaFavoritos (view: View) {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }
}