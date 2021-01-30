package md.merit.rickandmortyrest.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_main.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.adapters.MainAdapter
import md.merit.rickandmortyrest.data.DBHandler
import md.merit.rickandmortyrest.models.characters.Character
import md.merit.rickandmortyrest.remote.NetworkClient

class DisplayActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    lateinit var dbHandler: DBHandler
    var characterList: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
        dbHandler = DBHandler(this, null, null, 1)
        var intent = intent
        var aName = intent.getStringExtra("iName")
        var aLocation = intent.getStringExtra("iLocation")
        var aEpisode = intent.getStringExtra("iEpisode")
        var aStatus = intent.getStringExtra("iStatus")
        var aImage = intent.getStringExtra("iImage")

        actionBar.title = aName

        tvLocationDsp.text = aLocation
        tvEpisodeDsp.text = aEpisode
        tvStatusDsp.text = aStatus
        tvAlsoFrom.text = "Also from " + '"' + aLocation.toString() + '"'
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
        characterList = dbHandler.getCharactersFromLocation(aLocation!!)
        adapter.updateData(characterList)
    }

    private fun setUpAdapter() {
        rvDisplay.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(characterList, this)
        rvDisplay.adapter = adapter
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}