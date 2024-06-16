package app.artphoria.core.domain.usecase

import app.artphoria.core.util.Result
import app.artphoria.core.domain.repository.ArtRepository
import app.artphoria.core.domain.model.Art

/**
 * Use case which gets [Art]s
 */
class GetArtsUseCase(
    private val artRepository: ArtRepository
) {
    suspend operator fun invoke(page: Int = 1): List<Art> =
        when (val arts = artRepository.getCollection(page)) {
            is Result.Success -> arts.data
            is Result.Error -> emptyList() /* Could be handled in viewModel as well */
        }
}