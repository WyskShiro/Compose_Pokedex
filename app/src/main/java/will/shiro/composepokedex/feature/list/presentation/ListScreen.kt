package will.shiro.composepokedex.feature.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ListScreenRoute(
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ListScreen(
        viewModel::addPokemon,
        uiState
    )
}

@Composable
internal fun ListScreen(addPokemon: () -> Unit, uiState: ListUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = addPokemon) {
            Text(text = "Adicionar")
        }
        when (uiState) {
            ListUiState.Loading -> ListScreenLoading()
            is ListUiState.Success -> ListScreenSuccess(
                addPokemon,
                uiState.pokemons
            )
        }
    }
}

@Composable
internal fun ListScreenLoading() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun ListScreenSuccess(addPokemon: () -> Unit, pokemons: List<String>) {
    LazyColumn {
        items(pokemons) {
            Text(
                text = it,
                color = Color.Black,
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )
        }
    }
}
