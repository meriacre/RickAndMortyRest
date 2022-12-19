package md.merit.rickandmortyrest.models.characters

data class CharacterModel(
    val info: Info,
    val results: List<Result>
)