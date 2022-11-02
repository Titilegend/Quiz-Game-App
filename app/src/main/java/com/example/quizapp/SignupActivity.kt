package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding:ActivitySignupBinding
    val auth:FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding =ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)
        signupBinding.buttonSignup.setOnClickListener {
        val email = signupBinding.editTextSignupEmail.text.toString()
        val password = signupBinding.editTextSignupPassword.text.toString()
        signupWithFirebase(email,password)
        }

    }
    fun signupWithFirebase(email:String,password:String){
        signupBinding.progressBarSignup.visibility = View.VISIBLE
        //so the user can click signup button once
        signupBinding.buttonSignup.isClickable = false
        //function to create user account in firebase
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
        if(task.isSuccessful){
            Toast.makeText(applicationContext,"Your account has been created",Toast.LENGTH_SHORT).show()
            finish()
            signupBinding.progressBarSignup.visibility = View.INVISIBLE
            signupBinding.buttonSignup.isClickable = true
        }else{
            Toast.makeText(applicationContext,task.exception?.localizedMessage,Toast.LENGTH_SHORT).show()
        }
        }

    }
}