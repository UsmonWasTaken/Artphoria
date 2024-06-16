package app.artphoria.feature.arts.detail.model

import app.artphoria.core.domain.model.ArtObject

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val art: ArtObject) : DetailUiState
    data object Error : DetailUiState
}