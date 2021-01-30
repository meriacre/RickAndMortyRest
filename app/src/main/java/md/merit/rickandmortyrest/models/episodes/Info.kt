package md.merit.rickandmortyrest.models.episodes

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)