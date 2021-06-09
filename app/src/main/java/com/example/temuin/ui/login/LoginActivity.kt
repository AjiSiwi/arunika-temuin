package com.example.temuin.ui.login


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.temuin.R
import com.example.temuin.databinding.ActivityLoginBinding
import com.example.temuin.ui.survey.SurveyActivity
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
                binding.etEmail.error = getText(R.string.empty)
                binding.etEmail.requestFocus()
            }
            if (password.isEmpty()){
                binding.etPassword.error = getText(R.string.empty)
                binding.etPassword.requestFocus()
            }
            else{
                binding.progBar.visibility = View.VISIBLE
                loginRegisteredUser(email.toString(), password.toString())
            }
        }
    }
    private fun loginRegisteredUser(email:String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, R.string.log_success, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, SurveyActivity::class.java)
                    startActivity(intent)
                    binding.progBar.visibility = View.GONE
                }
            }
            .addOnFailureListener {
                binding.progBar.visibility = View.GONE
                Toast.makeText(this, R.string.log_failed, Toast.LENGTH_SHORT).show()
            }
    }
}