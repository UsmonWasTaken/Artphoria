package app.artphoria.core.domain.usecase

import app.artphoria.core.util.Result.Error
import app.artphoria.core.util.Result.Success
import app.artphoria.core.domain.repository.ArtRepository
import app.artphoria.core.domain.model.ArtObject

/**
 * Use case to get art detail.
 */
class GetArtDetailUseCase(
    private val artRepository: ArtRepository
) {
    suspend operator fun invoke(objectId: String): ArtObject? =
        when (val art = artRepository.getArt(objectId)) {
            is Success -> art.data
            is Error -> null /* Could be handled in viewModel as well */
        }
}