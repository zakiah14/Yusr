package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GroupslistActivity : AppCompatActivity() {
    lateinit var textviewgr : TextView
    lateinit var ref : DatabaseReference
   lateinit  var grlist : MutableList<Groups>
    lateinit var listgr:ListView
    lateinit var mAuthg :FirebaseAuth
lateinit var rolel:String
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groupslist)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        mAuthg=FirebaseAuth.getInstance()
        grlist= mutableListOf()
        listgr=findViewById(R.id.listgroupname)
        ref=FirebaseDatabase.getInstance().getReference("Groups")

        rolel = PreferenceManager.getDefaultSharedPreferences(this).getString("roleadmin", "")

    }

    override fun onStart() {
        super.onStart()
        rolel
        grplist()
    }
    fun grplist(){


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    grlist.clear()
                    for(g in p0.children){
                        val grp= g.getValue(Groups::class.java)
                        if(rolel.equals(grp!!.typ)) {
                            grlist.add(grp!!)
                        }
                    }
                    val adapter = GroupAdapter(applicationContext,R.layout.groplistfirebase,grlist)
                    listgr.adapter=adapter

                }
            }

        })

        listgr.setOnItemClickListener { parent, view, position, id ->

            var toastmessage= grlist[position]
            var ngroup= toastmessage.groupsname
            val intent = Intent(this@GroupslistActivity, ShowusersActivity::class.java)
            intent.putExtra("grnamelist", ngroup)
            startActivity(intent)
            // Toast.makeText(this,ngroup, Toast.LENGTH_LONG).show()
        }
    }


    fun gotoback(view: View){
        var intent = Intent(this, GroupActivity::class.java)
        startActivity(intent)
        finish()
    }
}
