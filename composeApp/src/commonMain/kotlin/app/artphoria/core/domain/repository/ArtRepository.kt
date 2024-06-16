package app.artphoria.core.domain.repository

import app.artphoria.core.util.Result
import app.artphoria.core.domain.model.Art
import app.artphoria.core.domain.model.ArtObject

/**
 * Data layer interface for the arts
 */
interface ArtRepository {
    /**
     * Returns a list of [Art]s.
     */
    suspend fun getCollection(page: Int): Result<List<Art>>

    /**
     * Returns an [ArtObject] with the given [objectId].
     */
    suspend fun getArt(objectId: String): Result<ArtObject>
}
