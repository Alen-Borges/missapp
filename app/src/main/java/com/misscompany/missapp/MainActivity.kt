package com.misscompany.missapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.misscompany.missapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    // Declaración de la instancia de FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar la vista y asignar el layout principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase antes de usar cualquier componente de Firebase
        FirebaseApp.initializeApp(this)

        val auth: FirebaseAuth

        // Inicializar la instancia de FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtener referencias a los EditTexts después de setContentView
        val mail = findViewById<EditText>(R.id.txtEmail)
        val password = findViewById<EditText>(R.id.txtPassword)

        val txtRegister = findViewById<TextView>(R.id.txtRegistrar)

        txtRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
//        val usuarioActual = auth.currentUser
//        // Recarga del usuario si ya está autenticado
//        usuarioActual?.reload()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
