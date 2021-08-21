package com.esvisoftech.tictactoe


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esvisoftech.tictactoe.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
val KEY1:String="Name1"
val KEY2:String="Name2"
lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startbtn.setOnClickListener {
            val i=Intent(this,play::class.java)
            i.putExtra("Name1",binding.name1.text.toString())
            i.putExtra("Name2",binding.name2.text.toString())
            startActivity(i)
        }
    }
}