package com.example.myapplication


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

class PostDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_details)

        /************************get all Ids *****************************/
        var author :TextView = findViewById( R.id.authordetail)
        var  content :TextView = findViewById( R.id.contentdetail)
        var  date :TextView = findViewById( R.id.datedetail)
        var nbupLong :TextView = findViewById( R.id.nblike)
        var  nbdownvote :TextView = findViewById( R.id.nbdislike)
        var  nbcomment :TextView = findViewById( R.id.nbcomment)
        var likeButtom :ImageButton =findViewById(R.id.like)
        var dislikeButtom :ImageButton =findViewById(R.id.dislike)
        var ValidateCommentButtom :Button =findViewById(R.id.buttoncomment)
        var addcomment : TextInputEditText =findViewById(R.id.addcommentinput)

        /**************************get bundle data ************************/

        val bundle: Bundle ? =intent.extras
        val authorbundele : String = bundle!!.getString("author").toString()
        val contentbundele : String = bundle!!.getString("content").toString()
        val datebundele : String = bundle!!.getString("date").toString()
        val upLongbundele : String = bundle!!.getLong("upLong")!!.toLong().toString()
        val downvotebundele : String = bundle!!.getLong("downvote")!!.toLong().toString()
        val commentbundele :Array<String>  = bundle!!.getStringArray("comment") as Array<String>

        /********************** pass bundle data to layout *******************/
        author.text = authorbundele
        content.text= contentbundele
        date.text=datebundele
        nbupLong.text=upLongbundele
        nbdownvote.text=downvotebundele
        nbcomment.text=commentbundele.size.toString()

        /*************************************ListViewComment Adapter ******************************/
        var ArrayAdapter=   ArrayAdapter<String>(this,R.layout.listcommentview,commentbundele )
        val listView = findViewById<View>(R.id.listcomment) as ListView
        listView.adapter = ArrayAdapter
        /*******************************Event ***********************************/

        likeButtom.setOnClickListener {

            nbupLong.text=(nbupLong.text.toString().toLong()+1).toString()
        }
        dislikeButtom.setOnClickListener {
            nbdownvote.text=(nbdownvote.text.toString().toLong()+1).toString()
        }
        ValidateCommentButtom.setOnClickListener {
            var GetTextAddComment :String= addcomment.text.toString()

        }


    }
}