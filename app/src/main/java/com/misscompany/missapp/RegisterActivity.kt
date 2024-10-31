package com.misscompany.missapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.misscompany.missapp.databinding.ActivityMainBinding
import com.misscompany.missapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    //Declarando instancia de firebase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mail = findViewById<EditText>(R.id.txtEmail)
        val password = findViewById<EditText>(R.id.txtPassword)
        val confirmedPassword = findViewById<EditText>(R.id.txtConfirmPassword)
        val btnLogin = findViewById<ImageButton>(R.id.btnLogIn)


        //Inicializar firebase
        auth = Firebase.auth

        //Crear nuevo usuario
        btnLogin.setOnClickListener{
            newUser(mail.text.toString(), password.text.toString(), confirmedPassword.text.toString())
        }

    }

    override fun onStart() {
        super.onStart()
        val usuarioActual = auth.currentUser
        // si el usuario existe, entonces hace reload
        usuarioActual?.reload()
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

    fun newUser(email: String, password: String, confirmedPassword: String) {
        if (password != confirmedPassword) {
            showErrorPopup(this, "Las contraseñas no coinciden.")
        } else {
            auth.createUserWithEmailAndPassword(email, email)
        }

    }

    fun showErrorPopup(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert) // Icono de alerta predeterminado
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss() // Cierra el diálogo al hacer clic en "Aceptar"
        }

        // Crea y muestra el diálogo
        val alertDialog = builder.create()
        alertDialog.show()
    }

}