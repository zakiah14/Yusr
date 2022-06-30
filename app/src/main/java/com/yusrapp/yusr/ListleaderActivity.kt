package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ListleaderActivity : AppCompatActivity() {


    lateinit var refu : DatabaseReference
    lateinit var refg : DatabaseReference
    lateinit  var urgrlist : MutableList<Registeration>
    lateinit var listurgr: ListView
    lateinit var mAuth:FirebaseAuth
lateinit var admin:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listleader)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)


        //show user in group leader
        val namegrleader: String? = intent.getStringExtra("grleader")
        Toast.makeText(this,namegrleader, Toast.LENGTH_LONG).show()
        urgrlist= mutableListOf()
        listurgr=findViewById(R.id.lidtlgr)
        refu= FirebaseDatabase.getInstance().getReference("Users")
        refu.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){

                   urgrlist.clear()
                    for(g in p0.children){
                        val grp= g.getValue(Registeration::class.java)
                       if(grp!!.usernamegroup==namegrleader && grp.typeuser != "leader"){

                            urgrlist.add(grp!!)

                        }
                    }
                    val adapterleader = LeadergroupAdapter(this@ListleaderActivity,R.layout.usergroup,urgrlist)
                   listurgr.adapter=adapterleader

                }
            }

        })


        //
    }

    fun gotoback(view: View){
        var intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
