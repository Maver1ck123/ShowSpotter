import com.example.showspotter.data.api.TitleApi
import com.example.showspotter.data.model.*
import com.example.showspotter.data.repository.TitleRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Test

class ShowSpotterUnitTests {

    @Test
    fun testTitleCreation() {
        val title = Title("1", "Inception", "movie", 2010, "8.8")
        assertEquals("1", title.id)
        assertEquals("Inception", title.title)
        assertEquals("movie", title.type)
        assertEquals(2010, title.year)
        assertEquals("8.8", title.imdbRating)
    }

    @Test
    fun testTitleListResponseToTitleList() {
        val titles = listOf(
            Title("1", "Movie1", "movie", 2020, "7.5"),
            Title("2", "Movie2", "movie", 2021, "8.0")
        )
        val response = TitleListResponse(titles)
        assertEquals(titles, response.toTitleList())
    }

    @Test
    fun testTitleDetailsResponseToTitleDetails() {
        val response = TitleDetailsResponse(
            id = "1",
            title = "Inception",
            plot_overview = "A mind-bending thriller",
            release_date = "2010-07-16",
            poster = "https://example.com/poster.jpg"
        )
        val details = response.toTitleDetails()
        assertEquals("1", details.id)
        assertEquals("Inception", details.title)
        assertEquals("A mind-bending thriller", details.description)
        assertEquals("2010-07-16", details.releaseDate)
        assertEquals("https://example.com/poster.jpg", details.posterUrl)
    }

    @Test
    fun testRepositoryGetCombinedTitles() {
        val mockApi = mockk<TitleApi>()
        val repository = TitleRepositoryImpl(mockApi)

        val mockMovies = listOf(Title("1", "Movie1", "movie", 2020, "7.5"))
        val mockShows = listOf(Title("2", "Show1", "show", 2021, "8.0"))

        every { mockApi.getMovies() } returns Single.just(TitleListResponse(mockMovies))
        every { mockApi.getTVShows() } returns Single.just(TitleListResponse(mockShows))

        val result = repository.getCombinedTitles().blockingGet()

        assertEquals(mockMovies, result.first)
        assertEquals(mockShows, result.second)
    }
}
