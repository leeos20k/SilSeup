package com.example.silseup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silseup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 데이터를 SharedPreferences에서 가져올 키 값
        val sharedPrefKey = "user_data"
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)

        // TODO: 로그인 기능 구현
        // 로그인을 위한 필요한 뷰 요소들과 기능들을 이곳에서 구현합니다.

        binding.btnLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            // SharedPreferences에서 회원가입 데이터 가져오기
            val savedUsername = sharedPref.getString("username", null)
            val savedPassword = sharedPref.getString("password", null)

            // 입력한 아이디와 비밀번호가 회원가입 데이터와 일치하는지 확인
            if (username == savedUsername && password == savedPassword) {
                // 로그인 성공
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()

                // 로그인 후에 MainActivity로 이동하거나 원하는 다른 동작을 구현할 수 있습니다.
                // 예시로 MainActivity로 이동하는 코드를 추가합니다.
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                // 로그인 실패
                Toast.makeText(this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}