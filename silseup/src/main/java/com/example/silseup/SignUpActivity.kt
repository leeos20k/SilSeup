package com.example.silseup

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.silseup.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 데이터를 SharedPreferences에 저장할 키 값
        val sharedPrefKey = "user_data"
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)

        // TODO: 회원가입 기능 구현
        // 회원가입을 위한 필요한 뷰 요소들과 기능들을 이곳에서 구현합니다.

        binding.btnSignUp.setOnClickListener {
            val username = binding.editTextId.text.toString()
            val password = binding.editTextPassword.text.toString()
            val email = binding.editTextEmail.text.toString()
            val phone = binding.editTextPhone.text.toString()

            // 입력한 데이터를 SharedPreferences에 저장
            with(sharedPref.edit()) {
                putString("username", username)
                putString("password", password)
                putString("email", email)
                putString("phone", phone)
                apply()
            }

            // 회원가입 완료 메시지 표시
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

            // 회원가입 완료 후에 로그인 화면으로 이동하거나 원하는 다른 동작을 구현할 수 있습니다.
            // 예시로 로그인 화면으로 이동하는 코드를 추가합니다.
            finish() // 회원가입 완료 후 현재 액티비티를 종료합니다.
        }
    }
}