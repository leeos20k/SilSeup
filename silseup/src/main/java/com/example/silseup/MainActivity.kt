package com.example.silseup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.example.silseup.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() { //AppCompatActivity를 상속하는 MainActivity

    private lateinit var binding: ActivityMainBinding //뷰바인딩을 이용하기위해 binding이라는 변수생성
    private lateinit var drawerLayout: DrawerLayout //드로어레이아웃을 이용허기 위해 drawerLayout이라는 변수생성
    private var isLoggedIn = false // // 로그인 상태를 저장하는 변수


    private fun checkLoginStatus() {  //로그인 상태를 확인하여 드로어 레이아웃의 버튼 상태를 조정하는 함수
        val sharedPrefKey = "user_data" //user_data 라는 문자열을 변수에 지정
        val sharedPref = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE) //쉐어드프리퍼런스에서 user_data의 값을 가져와서 sharedPref에 저장
        isLoggedIn = sharedPref.contains("id") && sharedPref.contains("password") //가져온 sharedPref에서 id와 password값을 저장


        if (isLoggedIn) {
            // 로그인 상태이면 드로어 레이아웃의 버튼을 '로그아웃'으로 변경, 회원가입 버튼 감추기
            binding.buttonLogout.isVisible = true
            binding.buttonLogin.isVisible = false
            binding.buttonSignUp.isVisible = false
        } else { //거짓이면
            // 로그아웃 상태이면 드로어 레이아웃의 버튼을 '로그인'으로 변경, 회원가입 버튼 보이기
            binding.buttonLogout.isVisible = false
            binding.buttonLogin.isVisible = true
            binding.buttonSignUp.isVisible = true
        }
    }


    private fun logout() {
        // TODO: 로그아웃 기능을 구현하는 함수
        isLoggedIn = false // 로그아웃 상태로 변경


        binding.buttonLogout.isVisible = false
        binding.buttonLogin.isVisible = true
        binding.buttonSignUp.isVisible = true

        // 네비게이션 드로어를 닫음
        drawerLayout.closeDrawer(GravityCompat.START)

        // 로그아웃되었다는 토스트 메시지 출력
        Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // 툴바를 액션바로 지정
        setSupportActionBar(binding.toolbar)

        // 드로어 레이아웃 초기화
        drawerLayout = binding.drawerLayout
        // 토글 버튼 추가
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        // TODO: 로그인 상태 확인 및 드로어 레이아웃의 버튼 상태 조정
        checkLoginStatus()




        // 회원가입 버튼 클릭 리스너 설정
        binding.buttonSignUp.setOnClickListener {
            // 회원가입 화면으로 이동
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼 클릭 리스너 설정
        binding.buttonLogin.setOnClickListener {
            // 로그인 화면으로 이동
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // TODO: 로그아웃 버튼 클릭 리스너 설정
        binding.buttonLogout.setOnClickListener {
            // 로그아웃 기능 구현
            logout()
        }
    }


    override fun onStart() {
        super.onStart()

        // 액티비티가 다시 시작될 때마다 로그인 상태를 다시 확인하여 버튼 상태를 조정
        checkLoginStatus()
    }
}