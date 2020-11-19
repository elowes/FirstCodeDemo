package com.example.firstcodedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : BaseActivity() {
    private lateinit var accountEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        accountEdit = findViewById(R.id.account)
        passwordEdit = findViewById(R.id.password)
        login = findViewById(R.id.login)
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if (account == "admin" && password == "123456") {
                val intent = Intent(this, OtherActivity::class.java)
                startActivity(intent)
                /// 销毁 LoginActivity
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_LONG).show()
            }
        }
    }
}