package com.example.temuin.ui.login


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.temuin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.etEmail.text
        val password = binding.etPassword.text

        binding.Register.setOnClickListener {
            onBackPressed()
        }

        binding.btnLogin.setOnClickListener {
            if (email.isEmpty()){
                binding.etEmail.error = "Email Salah"
                binding.etEmail.requestFocus()
            }
            if (password.isEmpty()){
                binding.etPassword.error = "Silahkan Mengisi password"
                binding.etPassword.requestFocus()
            }
            else{
                loginRegisteredUser(email.toString(), password.toString())
            }
        }
    }
    private fun loginRegisteredUser(email:String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "There's no Account Registered", Toast.LENGTH_SHORT).show()
                }

            }
            .addOnFailureListener {
                     Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
            }
    }
}