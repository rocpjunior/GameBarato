package com.example.gamebarato.ux

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.Login
import com.example.gamebarato.R

class CadastroPerfil : AppCompatActivity() {

    private var enderecoCadastrado: String = ""
    protected val resultado = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){resultadoAtividade ->
        if(resultadoAtividade.resultCode == RESULT_OK){
            val data: Intent? = resultadoAtividade.data
            val resultadoFinal = data?.getStringExtra("EnderecoCadastrado")
            enderecoCadastrado = resultadoFinal!!
            Toast.makeText(this, enderecoCadastrado, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_perfil)
       //val cep = intent.getStringExtra("CEP")


        val botaoCadastrarNovoEnd = findViewById<Button>(R.id.btnCadastrarNovEnd)
        val botaoCriarCadstro = findViewById<Button>(R.id.btnConfirmaCadastro)


        botaoCadastrarNovoEnd.setOnClickListener(this::telaNovoEndereco)
        botaoCriarCadstro.setOnClickListener(this::telaCadastroCriado)
    }

    fun telaNovoEndereco (view: View) {
        val intent = Intent(this, AdicionarEndereco::class.java)
        startActivity(intent)
    }

    fun telaCadastroCriado (view: View) {
        val intent = Intent(this, Login::class.java)
        finish()
    }
}