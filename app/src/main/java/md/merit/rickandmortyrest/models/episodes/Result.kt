package md.merit.rickandmortyrest.models.episodes

data class Result(
    val air_date: String? = "",
    val characters: List<String>? = null,
    val created: String? = "",
    val episode: String? = "",
    var id: Int? = 0,
    var name: String? = "",
    var url: String? = ""
)