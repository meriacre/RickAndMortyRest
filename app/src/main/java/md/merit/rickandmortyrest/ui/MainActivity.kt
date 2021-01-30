package md.merit.rickandmortyrest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.adapters.MainAdapter
import md.merit.rickandmortyrest.data.DBHandler
import md.merit.rickandmortyrest.models.characters.Character
import md.merit.rickandmortyrest.remote.NetworkClient

import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var networkClient: NetworkClient
    lateinit var dbHandler: DBHandler
    var characterList: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        networkClient = NetworkClient(this)
        dbHandler = DBHandler(this, null, null, 1)
        actionBar?.title = "Rick and Morty"

        if (dbHandler.dataCheck()) {
            networkClient.getFirstEpisode()
            networkClient.getData()
            Handler().postDelayed({
                dbHandler.updateEpisodeName()
                setUpAdapter()
                viewCharacters()
            }, 2000)
        } else {
            setUpAdapter()
            viewCharacters()
        }


        swipeRefresh.setOnRefreshListener {
            characterList.clear() // Remove all item
            viewCharacters()
            swipeRefresh.isRefreshing = false
        }
    }


    private fun viewCharacters() {
        characterList = dbHandler.getCharacters(this)
        adapter.updateData(characterList)
    }

    private fun setUpAdapter() {
        rvMain.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(characterList, this)
        rvMain.adapter = adapter
    }

}