package md.merit.rickandmortyrest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import md.merit.rickandmortyrest.R
import md.merit.rickandmortyrest.data.CharacterObject
import md.merit.rickandmortyrest.listeners.CharacterListener
import md.merit.rickandmortyrest.models.characters.Location
import md.merit.rickandmortyrest.models.characters.Result

import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CharacterListener {

    private lateinit var adapter: MainAdapter
    var characterList: ArrayList<Result> = ArrayList()
    var mainPresenter = MainPresenter()
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpAdapter()
        viewCharacters()

        swipeRefresh.setOnRefreshListener {
            characterList.clear() // Remove all item
            viewCharacters()
            swipeRefresh.isRefreshing = false
        }
    }


    private fun viewCharacters() {
//        characterList = dbHandler.getCharacters(this)
//        adapter.updateData(characterList)
        if (page <= 42) {
            mainPresenter.getCharacters(this, page)
        }else{
            CharacterObject.characterList = characterList
        }
    }

    private fun setUpAdapter() {
        rvMain.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(characterList, this)
        rvMain.adapter = adapter
    }

    override fun getCharacterList(list: ArrayList<Result>) {
        characterList.addAll(list)
        adapter.updateData(characterList)
        page++
        viewCharacters()
    }

    override fun getCharacterError(error: String) {
        Log.d("GetCharacterError", error)
    }

}