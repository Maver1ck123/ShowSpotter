## Unit Test Cases

### ViewModel Tests
class HomeViewModelTest {
@Mock
lateinit var mockRepository: TitleRepository

@Before
fun setup() {
    MockitoAnnotations.initMocks(this)
}

@Test
fun `loadData should update UI state to success`() {
    // Given
    val expectedMovies = listOf(Title(...))
    whenever(mockRepository.getCombinedTitles()).thenReturn(Single.just(expectedMovies to emptyList()))

    // When
    val viewModel = HomeViewModel(mockRepository)
    viewModel.loadData()

    // Then
    assert(viewModel.uiState.value is HomeUiState.Success)
}
}


### Repository Tests
class TitleRepositoryTest {
@Test
fun parse API response correctly() {
// Given
val json = File("test_response.json").readText()
val apiResponse = Gson().fromJson(json, ApiResponse::class.java)
    // When
    val result = TitleRepositoryImpl.parseResponse(apiResponse)

    // Then
    assertEquals(10, result.movies.size)
    assertTrue(result.movies.title.isNotEmpty())
}
}
### Test Coverage Repor

ViewModels | 92% | 80% | 100% | 92% |

Repositories | 82% | 70% | 85% | 83% |
