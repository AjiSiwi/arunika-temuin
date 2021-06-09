package com.example.temuin.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.temuin.R
import com.example.temuin.data.model.User
import com.example.temuin.databinding.ActivityRegisterBinding
import com.example.temuin.firestore.Firestore
import com.example.temuin.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
        val gender = binding.etGender.text

        binding.btnRegister.setOnClickListener {
            if (fullName.isEmpty()){
                binding.etFullName.error = getText(R.string.empty)
                binding.etFullName.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.etEmail.error = getText(R.string.empty)
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.etPassword.error = getText(R.string.empty)
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            if (gender.isEmpty()){
                binding.etGender.error = getText(R.string.empty)
                binding.etGender.requestFocus()
                return@setOnClickListener
            }
            else{
                binding.progBar.visibility = View.VISIBLE
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

                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    val user = User(
                        firebaseUser.uid,
                        binding.etFullName.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etGender.text.toString(),
                    )
                    Firestore().registerUser(this, user)

                    Toast.makeText(this, R.string.reg_success, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    binding.progBar.visibility = View.GONE
                }
                else{
                    binding.progBar.visibility = View.GONE
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun userRegistrationSuccess(){
        Toast.makeText(this, R.string.reg_success, Toast.LENGTH_SHORT).show()
        binding.progBar.visibility = View.GONE
    }
}
