package com.example.myapplication


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import com.android.volley.RequestQueue

class ListPostMain : AppCompatActivity() {
    private lateinit var recycle : RecyclerView
    private lateinit var Data : ArrayList<Post>

    /************** temporal variable to do our test ******************/
    private lateinit var author : Array<String>
    private lateinit var Date : Array<String>
    private lateinit var content : Array<String>
    private lateinit var title : Array<String>
    private lateinit var upLong : Array<Long>
    private lateinit var downvote : Array<Long>
    private lateinit var comment: ArrayList<Array<String>>
    private lateinit var comment1 :Array<String>
    private lateinit var comment2 :Array<String>
    private lateinit var comment3 :Array<String>

    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    /*************************create temporal data to test our recycleView *************/
        author = arrayOf(
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
            "adem", "houssam", "anis",
        )
        Date = arrayOf(
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
            "02/05/2021", "02/05/2022", "02/05/2022",
        )
        title = arrayOf(
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            "Footbal",
            "Informatique",
            "Etude",
            )
        content = arrayOf(
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",
            "Retrouvez toute l'actualité du football en direct sur L'Équipe. Découvrez toutes les dernières informations, résultats et classements sur tous les ...",
            "L'informatique s'intéresse à la mise en œuvre de méthodes scientifiques pour traiter l'information au moyen d'ordinateurs",
            "C'est quoi les études Effort intellectuel tourné vers l'acquisition de connaissances, vers l'apprentissage de quelque chose ",

        )

        upLong=arrayOf(30,40,50,30,40,50,30,40,50,30,40,50,30,40,50,30,40,50,30,40,50)
        downvote=arrayOf(50,60,10,50,60,10,50,60,10,50,60,10,50,60,10,50,60,10,50,60,10)
        comment1 = arrayOf(" bien dit " , " je trouve que ça pas bien" , " je pense que vous entrain de melanger ente .. et .."," je pense que vous entrain de melanger ente .. et .."," je pense que vous entrain de melanger ente .. et ..")
        comment2 = arrayOf(" bien dit " , " je trouve que ça pas bien" , " je pense que vous entrain de melanger ente .. et ..")
        comment3 = arrayOf(" bien dit " , " je trouve que ça pas bien" , " je pense que vous entrain de melanger ente .. et ..")


        comment= ArrayList()
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)
        comment.add(comment1)
        comment.add(comment2)
        comment.add(comment3)



        /******************************* init data *******************************/
        Data = arrayListOf<Post>()
        for (i in 0..6) {
            Data.add(
                Post(
                    id = 1,
                    Category = "",
                    parent = 2,
                    title = title[i],
                    upLong = upLong[i],
                    downVote = downvote[i],
                    author = author[i],
                    content = content[i],
                    date = Date[i],
                    comment = comment.get(i)

                )
            )
            /************************* init recycleView with data *********************/
            recycle = findViewById(R.id.Ryc)
            recycle.layoutManager = LinearLayoutManager(this)
            var adapter = PostAdapter(Data)
            recycle.adapter = adapter
            /********************* event click listner RecycleView to navigate to post details page ******/
            adapter.SetOnclickListner(object : PostAdapter.OnItemClickListner {
                override fun OnClickListener(position: Int) {
                    Log.e("adem", position.toString())

                    var intent = Intent(this@ListPostMain, PostDetails::class.java)
                    Log.e("adem", Data[position].content.toString())
                    intent.putExtra("content", Data[position].content)
                    intent.putExtra("author", Data[position].author)
                    intent.putExtra("date", Data[position].date)
                    intent.putExtra("upLong", Data[position].upLong)
                    intent.putExtra("downvote", Data[position].downVote)
                    intent.putExtra("comment", Data[position].comment)
                    startActivity(intent)

                }

            })

            /************************************create new post **************************/

            var createNewPost: FloatingActionButton = findViewById(R.id.createNewPost)

            createNewPost.setOnClickListener {
                var intent = Intent(this@ListPostMain, NewPost::class.java)
                // ID of the user
                intent.putExtra("idUser", 1)
                startActivity(intent)
            }


        }


    }
}