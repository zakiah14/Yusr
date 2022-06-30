package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Showplaces : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit  var pllist : MutableList<Places>
    lateinit var listpl: ListView
  lateinit var rolel:String
    lateinit var mAuthg: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showplaces)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        mAuthg= FirebaseAuth.getInstance()

        rolel = PreferenceManager.getDefaultSharedPreferences(this).getString("roleadmin", "")
        pllist= mutableListOf()
        listpl=findViewById(R.id.listplacename)
        ref= FirebaseDatabase.getInstance().getReference("Places")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    pllist.clear()
                    for(g in p0.children){
                        val grp= g.getValue(Places::class.java)
                        if(rolel.equals(grp!!.typp))
                        pllist.add(grp!!)
                    }
                    val adapter = PlacesAdapter(applicationContext,R.layout.placesshowfirebase,pllist)
                    listpl.adapter=adapter

                }
            }

        })
    }

    fun gotoback(view: View){
        var intent = Intent(this, GomapActivity::class.java)
        startActivity(intent)
        finish()
    }
}

