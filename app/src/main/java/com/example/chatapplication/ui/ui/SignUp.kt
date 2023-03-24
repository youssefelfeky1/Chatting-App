package com.example.chatapplication.ui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chatapplication.R
import com.example.chatapplication.ui.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtTxtEmail: EditText
    private lateinit var edtTxtPassword: EditText
    private lateinit var edtTxtName: EditText
    private lateinit var signupBtn: TextView

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDBRef:DatabaseReference





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtTxtEmail = findViewById(R.id.editTxtEmail)
        edtTxtPassword = findViewById(R.id.editTxtPassword)
        edtTxtName = findViewById(R.id.edtTxtName)
        signupBtn = findViewById(R.id.signupBtn)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        signupBtn.setOnClickListener {
            val email = edtTxtEmail.text.toString()
            val password =edtTxtPassword.text.toString()
            val name = edtTxtName.text.toString()
            signup(email,password,name)
        }
    }

    private fun signup(email:String,password:String,name:String){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {task->
            if(task.isSuccessful){
                addToDatabase(name,email,mAuth.currentUser?.uid!!)
                val intent = Intent(this@SignUp, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else{
                Toast.makeText(this@SignUp,"Some Error Occurred",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun addToDatabase(name: String, email: String, uid: String) {
        mDBRef=FirebaseDatabase.getInstance().getReference()
        mDBRef.child("User").child(uid).setValue(User(name,email,uid))

    }


}