package md.merit.rickandmortyrest.listeners

import md.merit.rickandmortyrest.models.episodes.Result

interface EpisodeListener {
    fun getEpisodes(list: ArrayList<Result>)
    fun getEpisodeError(error :String)
}