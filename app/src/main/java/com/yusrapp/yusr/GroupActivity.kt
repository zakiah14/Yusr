package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View

class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
    }
    fun gotoback(view:View){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun addgroup(view: View){
        var intent = Intent(this, AddgrpActivity::class.java)
        startActivity(intent)
    }

    fun showgroup(view: View){
        var intent = Intent(this, GroupslistActivity::class.java)
        startActivity(intent)
    }

}


