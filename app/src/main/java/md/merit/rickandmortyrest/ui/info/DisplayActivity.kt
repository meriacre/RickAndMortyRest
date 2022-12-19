package md.merit.rickandmortyrest.ui.info

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.lo_item.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.data.CharacterObject
import md.merit.rickandmortyrest.listeners.EpisodeListener
import md.merit.rickandmortyrest.ui.main.MainAdapter
import md.merit.rickandmortyrest.models.characters.Result

class DisplayActivity : AppCompatActivity(), EpisodeListener {

    private lateinit var adapter: MainAdapter
    var characterList: ArrayList<Result> = ArrayList()
    private lateinit var displayPresenter: DisplayPresenter
    var episodeUrl = ""
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        displayPresenter = DisplayPresenter()
        var intent = intent
        var aName = intent.getStringExtra("iName")
        var aLocation = intent.getStringExtra("iLocation")
        episodeUrl = intent.getStringExtra("iEpisode")!!
        var aStatus = intent.getStringExtra("iStatus")
        var aImage = intent.getStringExtra("iImage")

        characterList = CharacterObject.characterList.filter {
            it.location.name == aLocation
        } as ArrayList<Result>

        tvLocationDsp.text = aLocation
//        tvEpisodeDsp.text = aEpisode
        tvStatusDsp.text = aStatus
        tvAlsoFrom.text = "Also from " + '"' + aLocation.toString() + '"'
        findViewById<TextView>(R.id.tv_name).text = aName
        Picasso.with(this)
            .load(
                StringBuilder(aImage)
                    .toString()
            )
            .into(ivDsp)
        if (aStatus == "Alive") {
            ivSign.setColorFilter(Color.GREEN)
        } else if (aStatus == "Dead") {
            ivSign.setColorFilter(Color.RED)
        } else
            ivSign.setColorFilter(Color.YELLOW)


        setUpAdapter()
        getEpisodes()
    }

    private fun setUpAdapter() {
        rvDisplay.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(characterList, this)
        rvDisplay.adapter = adapter
    }

    private fun getEpisodes(){
        displayPresenter.getEpisodes(this, page)
    }

    override fun getEpisodes(list: ArrayList<md.merit.rickandmortyrest.models.episodes.Result>) {

        list.filter {
            if(it.url == episodeUrl)
                episodeUrl = it.name
            return
        }
        findViewById<TextView>(R.id.tvEpisodeDsp).text = episodeUrl
    }

    override fun getEpisodeError(error: String) {
        Log.d("getEpisodeError", error)
    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, MainActivity::class.java))
//    }
}