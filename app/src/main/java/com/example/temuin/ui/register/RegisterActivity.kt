package com.example.temuin.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.temuin.databinding.ActivityRegisterBinding
import com.example.temuin.firestore.Firestore
import com.example.temuin.data.UserEntity
import com.example.temuin.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val fullName = binding.etFullName.text
        val email = binding.etEmail.text
        val password = binding.etPassword.text

        binding.btnRegister.setOnClickListener {
            if (fullName.isEmpty()){
                binding.etFullName.error = "Nama Tidak Boleh Kosong"
                binding.etFullName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.etEmail.error = "Email Tidak Boleh Kosong"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.etPassword.error = "Password Tidak Boleh Kosong"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            else{
                registerUser(email.toString(), password.toString())
            }
        }

        binding.Login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){

                   // val firebaseUser: FirebaseUser = it.result!!.user!!
                    val user = UserEntity(
                       // firebaseUser.uid,
                        binding.etFullName.text.toString(),
                        binding.etPassword.text.toString(),
                        binding.etEmail.text.toString(),
                    )
                    Firestore().registerUser(this, user)

                    Toast.makeText(this, "Register Telah Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun userRegistrationSuccess(){
        Toast.makeText(this, "Register Telah Berhasil", Toast.LENGTH_SHORT).show()
    }
}
