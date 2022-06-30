package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    lateinit var mAuth : FirebaseAuth
    lateinit  var reflogin: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        mAuth = FirebaseAuth.getInstance()
    }

    fun gotoback(view:View){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun pilgrimmanage(view : View){

        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun leadermanager (view : View){

        var intent = Intent(this, LeaderActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun out(view : View){
        mAuth.signOut()
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun groupmanage(view : View){
        var intent = Intent(this, GroupActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun mapmanage(view : View){
        var intent = Intent(this, GomapActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun searchuser(view : View){
        var intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }
}
