package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ShowusersActivity : AppCompatActivity() {


    lateinit var ref : DatabaseReference
    lateinit  var urlist : MutableList<Registeration>
    lateinit var listur: ListView
   lateinit var rolel:String
    lateinit var mAuthg: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showusers)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        mAuthg= FirebaseAuth.getInstance()

        rolel = PreferenceManager.getDefaultSharedPreferences(this).getString("roleadmin", "")

        //val bundle: Bundle? = intent.extras
        val grname: String? = intent.getStringExtra("grnamelist")
        Toast.makeText(this,grname, Toast.LENGTH_LONG).show()
        urlist= mutableListOf()
        listur=findViewById(R.id.listviewuser)
        ref= FirebaseDatabase.getInstance().getReference("Users")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    urlist.clear()
                    for(g in p0.children){
                        val grp= g.getValue(Registeration::class.java)
                        if(grp!!.usernamegroup==grname && rolel.equals(grp.userallergies)){

                        urlist.add(grp!!)
                        }
                    }
                    val adapter = RegisterationAdapter(this@ShowusersActivity,R.layout.users,urlist)
                    listur.adapter=adapter

                }
            }

        })

     /*   listgr.setOnItemClickListener { parent, view, position, id ->

            var toastmessage= grlist[position]
            var ngroup= toastmessage.groupsname
            /*  val intent = Intent(this, ShowusersActivity::class.java)
              intent.putExtra("grnamelist", ngroup)
              startActivity(intent)*/
            Toast.makeText(this,ngroup, Toast.LENGTH_LONG).show()
        }*/


    }

    fun gotoback(view: View){
        var intent = Intent(this, GroupslistActivity::class.java)
        startActivity(intent)
        finish()
    }
}
