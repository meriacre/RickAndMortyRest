package md.merit.rickandmortyrest.models.characters

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)