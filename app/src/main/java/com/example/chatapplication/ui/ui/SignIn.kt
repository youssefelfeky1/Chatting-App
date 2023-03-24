package com.example.chatapplication.ui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chatapplication.R
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var edtTxtEmail:EditText
    private lateinit var edtTxtPassword:EditText
    private lateinit var signinBtn:TextView
    private lateinit var signupBtn:TextView

    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        edtTxtEmail = findViewById(R.id.editTxtEmail)
        edtTxtPassword = findViewById(R.id.editTxtPassword)
        signinBtn = findViewById(R.id.signinBtn)
        signupBtn = findViewById(R.id.signupBtn)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        signupBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        signinBtn.setOnClickListener {
            val email = edtTxtEmail.text.toString()
            val password = edtTxtPassword.text.toString()
            signin(email,password)
        }

    }
    private fun signin(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task->
            if(task.isSuccessful){
                val intent = Intent(this@SignIn, MainActivity::class.java)
                startActivity(intent)
                edtTxtEmail.text.clear()
                edtTxtPassword.text.clear()
            }else{
                Toast.makeText(this@SignIn,"User does not exist",Toast.LENGTH_SHORT).show()
            }
        }

    }
}