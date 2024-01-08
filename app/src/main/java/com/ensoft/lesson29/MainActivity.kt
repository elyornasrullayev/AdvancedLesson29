package com.ensoft.lesson29

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ensoft.lesson29.adapter.RvAdapter
import com.ensoft.lesson29.databinding.ActivityMainBinding
import com.ensoft.lesson29.model.User
import com.ensoft.lesson29.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private lateinit var adapter: RvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = RvAdapter(ArrayList<User>())
        binding.recyclerview.adapter = adapter

        viewModel.getAllData(this).observe(this, Observer{
            adapter.setData(it as ArrayList<User>)
            return@Observer
        })

        binding.btnDialog.setOnClickListener {
            showInsertDialog()
        }
    }
    private fun showInsertDialog(){
        builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(applicationContext).inflate(R.layout.insert_dialog, null)
        dialog = builder.create()
        dialog.setView(view)

        val et_name = view.findViewById<EditText>(R.id.et_username)
        val et_age = view.findViewById<EditText>(R.id.et_age)
        val btn_save = view.findViewById<Button>(R.id.btn_save)

        btn_save.setOnClickListener {
            if (et_name.text.isNotEmpty() && et_age.text.isNotEmpty()){
                val user = User(et_name.text.toString(), et_age.text.toString().toInt())
                saveDataIntoDatabase(user)
                dialog.dismiss()
            }
            else
                Toast.makeText(applicationContext, "Please fill up all the fields!", Toast.LENGTH_SHORT).show()


        }
        dialog.show()
    }
    private fun saveDataIntoDatabase(user: User){
        viewModel.insert(this, user)

    }
}