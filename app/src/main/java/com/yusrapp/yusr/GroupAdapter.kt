package com.yusrapp.yusr

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class GroupAdapter(val mctx: Context, val layoutid :Int, val grouplist :List<Groups>): ArrayAdapter<Groups>(mctx , layoutid , grouplist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mctx)
        val view : View = layoutInflater.inflate(layoutid,null)
        val textviewgroup= view.findViewById<TextView>(R.id.textviewname)
        val grdelete= view.findViewById<ImageView>(R.id.deletegr)
        //val showdetailsgr= view.findViewById<ImageView>(R.id.showdetails)
        val groupslist= grouplist[position]
        textviewgroup.text = groupslist.groupsname
        grdelete.setOnClickListener {
            deleteplaces(groupslist)
        }
        return view
    }


    fun deleteplaces(plc : Groups){
        val databasefire  = FirebaseDatabase.getInstance().getReference("Groups")
        val uidp : String = plc.groupsname
        databasefire.child(uidp).setValue(null)
        Toast.makeText(mctx,"Deleted is Done", Toast.LENGTH_LONG).show()


    }


}