package br.com.digio.digiobank.viewmodel

import app.cash.turbine.test
import br.com.digio.digiobank.data.mapper.toDomain
import br.com.digio.digiobank.data.model.DigioProductsResponse
import br.com.digio.digiobank.data.service.MockApiUtils
import br.com.digio.digiobank.domain.model.DigioProducts
import br.com.digio.digiobank.domain.usecase.GetProductsUseCase
import br.com.digio.digiobank.presentation.utils.ProductsResult
import br.com.digio.digiobank.presentation.viewmodel.MainViewModel
import com.google.gson.Gson
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val mainRule = MainDispatcherRule(UnconfinedTestDispatcher())

    private val useCase: GetProductsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        this.viewModel = MainViewModel(useCase)
    }

    @Test
    fun `when viewModel is created then call getProducts method`() = runTest {
        val mock = Gson().fromJson(MockApiUtils.successResponse, DigioProductsResponse::class.java)

        coEvery { useCase.invoke() } returns flowOf(mock.toDomain())

        viewModel.products.test {
            Assert.assertEquals(ProductsResult.Loading, awaitItem())

            viewModel.getProducts()

            Assert.assertEquals(ProductsResult.Success(mock.toDomain()), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `when getProducts is called then returns error`() = runTest {
        val errorFlow = flow<DigioProducts> {
            throw RuntimeException("Deu negado, fera!")
        }

        coEvery { useCase.invoke() } returns errorFlow

        viewModel.products.test {
            Assert.assertEquals(ProductsResult.Loading, awaitItem())

            viewModel.getProducts()

            val expected = awaitItem()

            Assert.assertTrue(expected is ProductsResult.Error)
            Assert.assertEquals((expected as ProductsResult.Error).message, "Deu negado, fera!")

            cancelAndConsumeRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
