package md.merit.rickandmortyrest.models.episodes

data class Episode(
    val info: Info,
    val results: List<Result>
)