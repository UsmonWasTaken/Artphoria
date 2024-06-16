package app.artphoria.core.data.repository

import app.artphoria.core.util.Result
import app.artphoria.core.util.Result.Error
import app.artphoria.core.util.Result.Success
import app.artphoria.core.data.network.KtorArtphoriaApi
import app.artphoria.core.data.network.model.NetworkArt
import app.artphoria.core.data.network.model.asArtObject
import app.artphoria.core.domain.model.Art
import app.artphoria.core.domain.model.ArtObject
import app.artphoria.core.domain.repository.ArtRepository

/**
 * Network backed implementation of the [ArtRepository].
 * @param artphoriaApi The data source for the arts.
 */
class ArtRepositoryImpl(
    private val artphoriaApi: KtorArtphoriaApi,
) : ArtRepository {

    override suspend fun getCollection(page: Int): Result<List<Art>> =
        try {
            val collection = artphoriaApi.getCollection(page)
            Success(
                collection.filter {
                    it.webImage != null
                }.map(NetworkArt::asArtObject)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e)
        }

    override suspend fun getArt(objectId: String): Result<ArtObject> =
        try {
            val art = artphoriaApi.getDetail(objectId)
            Success(art.asArtObject())
        } catch (e: Exception) {
            e.printStackTrace()
            Error(e)
        }
}