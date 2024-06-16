package app.artphoria.core.data.network

import app.artphoria.core.data.network.model.CollectionNetworkResponse
import app.artphoria.core.data.network.model.DetailNetworkResponse
import app.artphoria.core.data.network.model.NetworkArt
import app.artphoria.core.data.network.model.NetworkArtObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val PS = "ps"
private const val PAGING_PAGE_SIZE = 100
const val HOST = "www.rijksmuseum.nl"
const val PATH = "api/en/"
const val COLLECTION = "collection"
const val PAGE = "p"

class KtorArtphoriaApi(
    private val client: HttpClient,
) {
    suspend fun getCollection(page: Int): List<NetworkArt> =
        client.get("$COLLECTION?&$PAGE=$page&$PS=$PAGING_PAGE_SIZE")
            .body<CollectionNetworkResponse>().artObjects


    suspend fun getDetail(objectId: String): NetworkArtObject =
        client.get("$COLLECTION/$objectId")
            .body<DetailNetworkResponse>().artObject
}