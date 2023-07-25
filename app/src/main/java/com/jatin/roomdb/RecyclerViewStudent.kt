package com.jatin.roomdb

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewStudent(private var studenArray: ArrayList<StudentEntiity>,
                          var itemClick: MainActivity
): RecyclerView.Adapter<RecyclerViewStudent.ViewHolder>()
{
    //  create an inner class to declare id of xml file
    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        var tvName = view.findViewById<TextView>(R.id.tvName)!!
        var del = view.findViewById<Button>(R.id.btnDel)!!
        var edit = view.findViewById<Button>(R.id.btnEdit)!!
    }
    //inflating the view which we have customized
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false)
        return ViewHolder(view)
    }

    // applying operation on id of view of customized view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.tvName.setText(studenArray[position].name).toString()
        holder.del.setOnClickListener {

            itemClick.deleteStudentData(position)

        }
        holder.edit.setOnClickListener {

            itemClick.updateStudentData(studenArray[position].rollNo)
        }
    }


    // returning count of items of customized view
    override fun getItemCount(): Int {
        return studenArray.size
    }


}