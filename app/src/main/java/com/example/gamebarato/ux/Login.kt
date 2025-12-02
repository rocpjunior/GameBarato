package com.example.gamebarato.ux

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.R
import com.example.gamebarato.ux.jogo.MainActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val botaoCadastro = findViewById<Button>(R.id.btnCriarCadastro)
        val botaoLogin = findViewById<Button>(R.id.btnFazerLogin)

        botaoCadastro.setOnClickListener(this::telaCadastro)
        botaoLogin.setOnClickListener(this::telaLogin)
    }


    fun telaCadastro (view: View) {
        val intent = Intent(this, CadastroPerfil::class.java)
        startActivity(intent)
    }
    fun telaLogin (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}