package will.shiro.composepokedex.feature.list.presentation

sealed class ListUiState {
    object Loading: ListUiState()

    data class Success(
        val pokemons: List<String>
    ): ListUiState()
}