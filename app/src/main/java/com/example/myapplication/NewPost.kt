package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class NewPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)
        var titlePost : TextView = findViewById( R.id.titlePost)
        var  contentpost : TextView = findViewById( R.id.contentpost)
        var  createPost : Button = findViewById( R.id.createPost)


        /********************* get id of the user *************************/
        val bundle: Bundle ? =intent.extras
        val idUser : String = bundle!!.getInt("idUser").toString()
        createPost.setOnClickListener {
            val title = titlePost.text.toString()
            val content= contentpost.text.toString()

            /******** send data to backend****/

            Toast.makeText(this,"your post created , thanks ",Toast.LENGTH_SHORT).show()
        }

    }
}