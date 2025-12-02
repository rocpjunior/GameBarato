package com.example.gamebarato.ux

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.CadastroPerfil
import com.example.gamebarato.R

class AdicionarEndereco : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adicionar_endereco)
        val botaoCadastrarEndereco = findViewById<Button>(R.id.btnCadastrarEndereco)
        botaoCadastrarEndereco.setOnClickListener(this::salvandoEndereco)
    }

    protected fun salvandoEndereco(view: View) {

        val edCEP = findViewById<EditText>(R.id.txtCep)
        val edApelido = findViewById<EditText>(R.id.txtApelido)
        val edLogradouro = findViewById<EditText>(R.id.txtLogradouro)
        val edNumero = findViewById<EditText>(R.id.txtNumero)
        val edComplemento = findViewById<EditText>(R.id.txtComplemento)
        val edReferencia = findViewById<EditText>(R.id.txtReferencia)
        val edBairro = findViewById<EditText>(R.id.txtBairro)
        val edCidade = findViewById<EditText>(R.id.txtCidade)
        val edUf = findViewById<EditText>(R.id.txtUF)

        var cep = edCEP.text.toString()
        var apelido = edApelido.text.toString()
        var logradouro = edLogradouro.text.toString()
        var numero = edNumero.text.toString()
        var complemento = edComplemento.text.toString()
        var referencia = edReferencia.text.toString()
        var bairro = edBairro.text.toString()
        var cidade = edCidade.text.toString()
        var uf = edUf.text.toString()

        val intencao = Intent(this, CadastroPerfil::class.java)
        intencao.putExtra("CEP", cep)
        intencao.putExtra("Apelido", apelido)
        intencao.putExtra("Logradouro", logradouro)
        intencao.putExtra("Numero", numero)
        intencao.putExtra("Complemento", complemento)
        intencao.putExtra("Referencia", referencia)
        intencao.putExtra("Bairro", bairro)
        intencao.putExtra("Cidade", cidade)
        intencao.putExtra("UF", uf)
        if (cep.isEmpty() && apelido.isEmpty() && logradouro.isEmpty() && numero.isEmpty() && complemento.isEmpty() && referencia.isEmpty() && bairro.isEmpty() && cidade.isEmpty() && uf.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos com *", Toast.LENGTH_SHORT).show()
        } else {
            //resultado.launch(intencao)
        }

        val botaoCadastrarEndereco = findViewById<Button>(R.id.btnCadastrarEndereco)
        botaoCadastrarEndereco.setOnClickListener{
            val resultadoIntencao = Intent()
            resultadoIntencao.putExtra("EnderecoCadastrado", """
                """.trimIndent())
            setResult(RESULT_OK, resultadoIntencao)
            this.finish()
        }
    }
}