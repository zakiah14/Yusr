package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View

class GomapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gomap)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
    }
    fun gotoback(view:View){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun addmaps(view: View){
        var intent = Intent(this, Showmaps::class.java)
        startActivity(intent)
    }

    fun showplaces(view: View){
        var intent = Intent(this, Showplaces::class.java)
        startActivity(intent)
    }

}
