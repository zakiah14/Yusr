package com.yusrapp.yusr

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class LeadergroupAdapter(val mctxgr: Context, val layoutidgr :Int, val userlistgr :List<Registeration>): ArrayAdapter<Registeration>(mctxgr , layoutidgr , userlistgr) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflateruser: LayoutInflater = LayoutInflater.from(mctxgr)
        val viewuser : View = layoutInflateruser.inflate(layoutidgr,null)
        val textviewnamel= viewuser.findViewById<TextView>(R.id.grlgr)
        val usergrl= viewuser.findViewById<ImageView>(R.id.userlgr)
        val userslist= userlistgr[position]
        textviewnamel.text=userslist.usernamelist


        usergrl.setOnClickListener {
            showdetails(userslist)
        }

        return viewuser
    }

    fun showdetails(reg : Registeration){


        val builder = AlertDialog.Builder(mctxgr)
        builder.setTitle("Details User")
        val inflater = LayoutInflater.from(mctxgr)
        val view: View = inflater.inflate(R.layout.detailsuser,null)
        val detnameuser=view.findViewById<TextView>(R.id.namedet)
        val detcardiduser=view.findViewById<TextView>(R.id.cardiddet)
        val detphoneuser=view.findViewById<TextView>(R.id.phonedet)
        val detageuser=view.findViewById<TextView>(R.id.agedet)
        val dettestblooduser=view.findViewById<TextView>(R.id.blooddet)
        val detpressureuser=view.findViewById<TextView>(R.id.pressuredet)
        val detdiabetesuser=view.findViewById<TextView>(R.id.diabetesdet)
        val detmaleuser=view.findViewById<TextView>(R.id.genderdet)
        val dettypeuser=view.findViewById<TextView>(R.id.typeuserdet)
        detnameuser.setText(reg.usernamelist)
        detcardiduser.setText(reg.cardiduser)
        detphoneuser.setText(reg.numberphone)
        detageuser.setText(reg.userage)
        dettestblooduser.setText(reg.userblood)
        detpressureuser.setText(reg.userpressure)
        detdiabetesuser.setText(reg.userdiabetes)
        detmaleuser.setText(reg.usergender)
        dettypeuser.setText(reg.typeuser)
        builder.setView(view)
        builder.setNegativeButton("CLOSE") { dialog, which ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }



}