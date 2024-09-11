package com.example.apkjornada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class cadastrargestante : AppCompatActivity() {

    private lateinit var nomeGestanteEditText: EditText
    private lateinit var cpfEditText: EditText
    private lateinit var susEditText: EditText
    private lateinit var nomeCriancaEditText: EditText
    private lateinit var idadeEditText: EditText
    private lateinit var estadoEditText: EditText
    private lateinit var cidadeEditText: EditText
    private lateinit var cepEditText: EditText

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastrargestante)

        database = FirebaseDatabase.getInstance().reference.child("gestantes")

        nomeGestanteEditText = findViewById(R.id.signup_name)
        cpfEditText = findViewById(R.id.cpf)
        susEditText = findViewById(R.id.sus)
        nomeCriancaEditText = findViewById(R.id.crianca)
        idadeEditText = findViewById(R.id.oidade)
        cidadeEditText = findViewById(R.id.idcidade)
        estadoEditText = findViewById(R.id.estado)
        cepEditText = findViewById(R.id.ocep)

        val salvarButton: Button = findViewById(R.id.buttonViewatualizar)
        salvarButton.setOnClickListener {
            adicionarCliente()
        }
    }

    private fun adicionarCliente() {
        val nomeGestante = nomeGestanteEditText.text.toString().trim()
        val cpf = cpfEditText.text.toString().trim()
        val sus = susEditText.text.toString().trim()
        val nomeCrianca = nomeCriancaEditText.text.toString().trim()
        val idade = idadeEditText.text.toString().toIntOrNull() ?: 0
        val estado = estadoEditText.text.toString().trim()
        val cidade = cidadeEditText.text.toString().trim()
        val cep = cepEditText.text.toString().trim()

        val clienteId = database.push().key // Gera uma chave única para o cliente

        if (!nomeGestante.isEmpty() && !cpf.isEmpty() && !sus.isEmpty() && !nomeCrianca.isEmpty()
            && idade > 0  && !estado.isEmpty() && !cidade.isEmpty() && !cep.isEmpty()) {
            val cliente = Customer(nomeGestante, cpf, sus, nomeCrianca, idade.toString(), estado, cidade, cep)
            clienteId?.let {
                database.child(it).setValue(cliente)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Gestante adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                        limparCampos()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao adicionar Gestante", Toast.LENGTH_SHORT).show()
                    }
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limparCampos() {
        nomeGestanteEditText.text.clear()
        cpfEditText.text.clear()
        susEditText.text.clear()
        nomeCriancaEditText.text.clear()
        idadeEditText.text.clear()
        estadoEditText.text.clear()
        cidadeEditText.text.clear()
        cepEditText.text.clear()
    }
}
