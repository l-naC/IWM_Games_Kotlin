package com.example.iwm_games_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layou_fragment_list.*

class FragmentList : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layou_fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this.activity)
        val URL = "https://my-json-server.typicode.com/bgdom/cours-android/games"

        val queue = Volley.newRequestQueue(this.activity)
        val stringRequest = StringRequest(
            Request.Method.GET, URL,
            Response.Listener<String> { response ->
                Array<Game>::class.java
                val games = Gson().fromJson(response, Array<Game>::class.java);
                val obj = object : AdapterInterface {
                    override fun open(game: Game) {}
                    override val games: Array<Game> = games
                }
                recycler.adapter = GamesAdapter(obj)
            },
            Response.ErrorListener {

            })
        queue.add(stringRequest)
        super.onViewCreated(view, savedInstanceState)
    }
}

interface AdapterInterface {
    val games: Array<Game>
    fun open(game: Game): Unit
}

data class Game(
    val id: Int,
    val name: String,
    val description: String,
    val link: String,
    val img: String
)
