package com.example.iwm_games_kotlin

import android.os.Bundle
import android.util.Log
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
        swipe.setOnRefreshListener {
            fetchData(this)
        }

        fetchData(this)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchData(context: FragmentList) {
        swipe.isRefreshing = true;
        recycler.layoutManager = LinearLayoutManager(this.activity)
        val URL = "https://my-json-server.typicode.com/bgdom/cours-android/games"

        val queue = Volley.newRequestQueue(this.activity)
        val request = StringRequest(Request.Method.GET, URL,
            Response.Listener<String> { response ->
                Array<Game>::class.java
                swipe.isRefreshing = false
                val games = Gson().fromJson(response, Array<Game>::class.java);
                val obj = object : AdapterInterface {
                    override val games: Array<Game> = games
                    override fun open(game: Game) {
                        this@FragmentList
                            .fragmentManager!!
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment,FragmentView(game))
                            .commit()
                    }
                }
                recycler.adapter = GamesAdapter(obj)
            }, Response.ErrorListener { error -> Log.e("test", error.localizedMessage) })
        queue.add(request)
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
