package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*

class  ProfileActivity : AppCompatActivity() {
    lateinit var  mAuth: FirebaseUser
    lateinit var nameofuser:TextView
    lateinit var phoneofuser:TextView
    lateinit var groupofuser:TextView
    lateinit var cardofuser:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        mAuth= FirebaseAuth.getInstance().currentUser!!
        var uiduser=mAuth.uid
        nameofuser=findViewById(R.id.namepr)
        phoneofuser=findViewById(R.id.phonepr)
        groupofuser=findViewById(R.id.ngroppr)
        cardofuser=findViewById(R.id.cardidpr)

        var ref= FirebaseDatabase.getInstance().getReference("Usres")

        ref.child(uiduser).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){

                        val grp= p0.getValue(Registeration::class.java)
                       var namerp=grp!!.usernamelist
                    var phonerp=grp!!.numberphone
                    var grprp=grp!!.usernamegroup
                    var cardrp=grp!!.cardiduser
                    nameofuser.setText(namerp)
                    phoneofuser.setText(namerp)
                    groupofuser.setText(namerp)
                    cardofuser.setText(namerp)

                }
            }

        })
    }
    fun gotoback(view: View){
        var intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
