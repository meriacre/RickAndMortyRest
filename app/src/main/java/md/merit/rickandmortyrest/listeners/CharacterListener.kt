package md.merit.rickandmortyrest.listeners

import md.merit.rickandmortyrest.models.characters.Result

interface CharacterListener {
    fun getCharacterList(list: ArrayList<Result>)
    fun getCharacterError(error: String)
}