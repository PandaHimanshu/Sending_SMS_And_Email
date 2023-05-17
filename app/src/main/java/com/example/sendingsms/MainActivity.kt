package com.example.sendingsms

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    lateinit var etmessage:EditText
    lateinit var etnumber:EditText
    lateinit var btnsend:Button
    lateinit var btnEmail:Button

    var usermessage:String=""
    var usernumber:String=""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etmessage=findViewById(R.id.editTextTextMessage)
        etnumber=findViewById(R.id.editTextNumber)
        btnsend=findViewById(R.id.buttonSend)
        btnEmail=findViewById(R.id.buttonEmail)

        btnsend.setOnClickListener {
            usermessage=etmessage.text.toString()
            usernumber=etnumber.text.toString()

            sendSMS(usermessage,usernumber)
        }

        btnEmail.setOnClickListener {
            val intent=Intent(this,EmailActivity::class.java)
            startActivity(intent)

        }
    }

    fun sendSMS(usermessage:String,usernumber:String){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS)
            !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),1)
        }
        else{
            val smsmanager:SmsManager
            if (Build.VERSION.SDK_INT>=23){
                 smsmanager =this.getSystemService(SmsManager::class.java)
            }
            else{
                smsmanager =SmsManager.getDefault()
            }
                smsmanager.sendTextMessage(usernumber,null,usermessage,null,null)
            Toast.makeText(applicationContext,"Message sent",Toast.LENGTH_LONG).show()
        }
    }
    }
