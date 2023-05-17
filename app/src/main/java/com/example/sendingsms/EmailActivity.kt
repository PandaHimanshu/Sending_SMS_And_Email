package com.example.sendingsms

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText

class EmailActivity : AppCompatActivity() {

    lateinit var etEmail:EditText
    lateinit var etsubject:EditText
    lateinit var ettext:EditText
    lateinit var sendbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        etEmail=findViewById(R.id.editTextEmail)
        etsubject=findViewById(R.id.editTextSubject)
        ettext=findViewById(R.id.editTextMessage)
        sendbtn=findViewById(R.id.buttonSendEmail)

        sendbtn.setOnClickListener {
            val userEmail=etEmail.text.toString()
            val usersubject=etsubject.text.toString()
            val usertext=ettext.text.toString()

            sendEmail(userEmail,usersubject,usertext)
        }
    }

    fun sendEmail(userEmail:String,usersubject:String,usertext:String){
        val emailadress= arrayOf(userEmail)
        val emailintent=Intent(Intent.ACTION_SEND)
//        emailintent.data= Uri.parse("mailto:")
        emailintent.type="*/*"
        emailintent.putExtra(Intent.EXTRA_EMAIL,emailadress)
        emailintent.putExtra(Intent.EXTRA_SUBJECT,usersubject)
        emailintent.putExtra(Intent.EXTRA_TEXT,usertext)

        if(emailintent.resolveActivity(packageManager)!=null){
            startActivity(Intent.createChooser(emailintent,"choose an app"))
        }
    }
}

