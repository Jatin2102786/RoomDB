package com.jatin.roomdb

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jatin.roomdb.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity(),ItemClick {
    //declaring binding
    private lateinit var binding: ActivityMainBinding

    // declaring recycler view
    private lateinit var studentRecycler: RecyclerViewStudent

    // declaring student array
    private var studentArray = arrayListOf<StudentEntiity>()

    // Declaring database class
    private lateinit var roomDB: RoomDB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initialling binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialling recycler view
        studentRecycler = RecyclerViewStudent(studentArray, this)
        // Constructing database
        roomDB = RoomDB.createDatabase(this)!!
        //returning adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = studentRecycler
        //showing room database data
        getStudentData()
        // implementing click on fab button
        binding.fabBtnToAdd.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_add_element)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.show()
            val etName = dialog.findViewById<EditText>(R.id.etName)
            val add = dialog.findViewById<Button>(R.id.btnAdd)
            val cancel = dialog.findViewById<Button>(R.id.btnCancel)
            add.setOnClickListener {
                class insertStudentData : AsyncTask<Void, Void, Void>()
                {
                    @Deprecated("Deprecated in Java")
                    override fun doInBackground(vararg p0: Void?): Void? {
                        val studentEntiity = StudentEntiity(name = etName.text.toString())
                        roomDB.StudentDao().insertStudentData(studentEntiity)
                        return null
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onPostExecute(result: Void?)
                    {
                        super.onPostExecute(result)
                        Toast.makeText(this@MainActivity, "data entered successfully", Toast.LENGTH_SHORT).show()
                        getStudentData()
                    }
                }
                insertStudentData().execute()
                dialog.dismiss()
            }
            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    fun getStudentData()
    {
        studentArray.clear()
        class getInfo : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                studentArray.addAll(roomDB.StudentDao().getStudentDAta())
                return null
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onPostExecute(result: Void?)
            {
                super.onPostExecute(result)
                studentRecycler.notifyDataSetChanged()
            }
        }
        getInfo().execute()
    }

    override fun deleteStudentData(position: Int)
    {
        class deleteStudent:AsyncTask<Void,Void,Void>()
        {
            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg p0: Void?): Void?
            {
                roomDB.StudentDao().deleteStudednt(studentArray[position])
                return null
            }
            @Deprecated("Deprecated in Java")
            @SuppressLint("NotifyDataSetChanged")
            override fun onPostExecute(result: Void?)
            {
                super.onPostExecute(result)
                Toast.makeText(this@MainActivity, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
                getStudentData()
            }
        }
        deleteStudent().execute()

    }


    override fun updateStudentData(position: Int)
    {
        var studentEntiity= StudentEntiity(rollNo = position, name ="Harsh" )

        class updateStudent:AsyncTask<Void,Void,Void>()
        {
            override fun doInBackground(vararg p0: Void?): Void?
            {
               roomDB.StudentDao().updateStudent(studentEntiity)
                return null
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onPostExecute(result: Void?)
            {
                super.onPostExecute(result)
                Toast.makeText(this@MainActivity, "Updated Successfully!", Toast.LENGTH_SHORT).show()
               getStudentData()
            }

        }
        updateStudent().execute()

    }

}