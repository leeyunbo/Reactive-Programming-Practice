package com.leeyunbo.realmvvmpractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.leeyunbo.realmvvmpractice.R
import com.leeyunbo.realmvvmpractice.data.UserVO
import com.leeyunbo.realmvvmpractice.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userObserver = Observer<UserVO> { user ->
            userlist_tv.text = user.toString()
        }

        viewModel.user.observe(this, userObserver)

        get_button.setOnClickListener(View.OnClickListener {
            requestUser()
        })

        main_move_bt.setOnClickListener {
            val intent: Intent = Intent(this, com.leeyunbo.realmvvmpractice.ui.FeesCalcActivity::class.java)
            startActivity(intent)
        }
    }

    private fun requestUser() {
        viewModel.getUser(nickname_edittext.text.toString())
    }
}
