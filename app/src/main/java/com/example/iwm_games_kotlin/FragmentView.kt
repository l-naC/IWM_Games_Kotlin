package com.example.iwm_games_kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_activity_webview.*
import kotlinx.android.synthetic.main.layout_fragment_view.*
import android.util.Log.d as d1

class FragmentView(private val game: Game) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        description.text = game.description;
        name.text = game.name;
        Picasso.get().load(this.game.img).into(img);

        button.setOnClickListener {
            /**val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(game.link)
            startActivity(openURL)*/
            val value: String = Uri.parse(game.link).toString()
            val openWEBVIEW = Intent(this.context, ActivityWebView::class.java)
            openWEBVIEW.putExtra("link", value)
            startActivity(openWEBVIEW)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}
