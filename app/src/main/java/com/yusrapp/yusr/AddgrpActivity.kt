package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddgrpActivity : AppCompatActivity() {
    lateinit var groupname: EditText
    var dttype:String="0"
    lateinit var mAuth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgrp)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        groupname = findViewById(R.id.namegroupreg)
        mAuth = FirebaseAuth.getInstance()
        getuid()

        Toast.makeText(this@AddgrpActivity, dttype,
            Toast.LENGTH_SHORT).show()
    }
fun gotoback(view:View){
    var intent = Intent(this, GroupActivity::class.java)
    startActivity(intent)
    finish()
}
    fun addgroupbtn(view: View) {

        var grpname = groupname.text.toString()
        if(grpname.trim().isEmpty() ){
            Toast.makeText(this@AddgrpActivity,"Please Fill your field ",Toast.LENGTH_SHORT).show()
        }else{
        // Sign up success, update UI with signed-in user's information
        val ref = FirebaseDatabase.getInstance().getReference("Groups")
        var namegr= Groups(grpname, dttype)
        val usersid = ref.push().key
        if (usersid != null) {
            ref.child(grpname)
                    .setValue(namegr)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(this, "Group is Added sucsffuly",
                                    Toast.LENGTH_LONG).show()

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(this, "" + task.getException(),
                                    Toast.LENGTH_LONG).show()
                        }
                    }
        }

    }}
    fun getuid(){

        var uiduser=mAuth.currentUser!!.uid
       var reflogin= FirebaseDatabase.getInstance().getReference("Users")
        reflogin.child(uiduser).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    val checktype = p0.getValue(Registeration::class.java)
                    dttype=checktype!!.typeuser

                }

                /* if(checkcount != 1){

                 }*/
            }

        })




        //************

    }
}
