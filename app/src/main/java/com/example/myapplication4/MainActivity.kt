package com.example.myapplication4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var ediTextEmail: EditText
    private lateinit var editTextpassword : EditText
    private lateinit var editTextConfirmpassword : EditText
    private lateinit var buttonRegister : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }
    private fun init() {
        ediTextEmail = findViewById(R.id.editTextEmail)
        editTextpassword = findViewById(R.id.editTextpassword)
        editTextConfirmpassword = findViewById(R.id.editTextConfirmpassword)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val email = ediTextEmail.text.toString()
            val password = editTextpassword.text.toString()
            val confirmpassword = editTextConfirmpassword.text.toString()

            if(email.isEmpty() or password.isEmpty() or confirmpassword.isEmpty()){
                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 9){
                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
            }
            if(password != confirmpassword){
                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
            }


           FirebaseAuth.getInstance()
               .createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener { task ->
                   if (task.isSuccessful){
                       Toast.makeText(this, "congrats!", Toast.LENGTH_SHORT).show()
                   }
               }
        }

    }
}