package com.yusrapp.yusr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ForgotpassActivity : AppCompatActivity() {
    lateinit var cardidus: EditText
    lateinit var phoneus: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setCustomView(R.layout.bar)
        setContentView(R.layout.activity_forgotpass)
        cardidus=findViewById(R.id.cardforgetlast)
        phoneus=findViewById(R.id.phoneforgetlast)


    }
    fun gotoback(view:View){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun forgerpass(view: View){

        var ref= FirebaseDatabase.getInstance().getReference("Users")
        var idcardfor=cardidus.text.toString()
        var numphone=phoneus.text.toString()
        if(idcardfor.trim().isEmpty() or numphone.trim().isEmpty()){
            Toast.makeText(this@ForgotpassActivity,"Please Fill your fields ", Toast.LENGTH_SHORT).show()
        }else{
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                  //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0.exists()) {
                        var countch=0
                        for (g in p0.children) {
                            val grp = g.getValue(Registeration::class.java)
                            var ucard = grp!!.cardiduser
                            var passfor = grp!!.pass
                            var numberrp = grp!!.numberphone

                            if(idcardfor.equals(ucard) && numphone.equals(numberrp) ) {
                                Toast.makeText(this@ForgotpassActivity,"nadiaa", Toast.LENGTH_LONG).show()
                               countch=1
                                val smsManager = SmsManager.getDefault() as SmsManager
                                smsManager.sendTextMessage(numberrp, null, "YOUR PASSWORD: " + passfor, null, null)
                            }

                        }
                        if(countch == 0){
                            Toast.makeText(this@ForgotpassActivity,"your phone or card number is Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }}
}
