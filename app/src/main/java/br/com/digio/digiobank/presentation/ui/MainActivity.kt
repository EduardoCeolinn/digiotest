package br.com.digio.digiobank.presentation.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digio.digiobank.R
import br.com.digio.digiobank.databinding.ActivityMainBinding
import br.com.digio.digiobank.domain.model.DigioProducts
import br.com.digio.digiobank.presentation.ui.products.ProductAdapter
import br.com.digio.digiobank.presentation.ui.spotlight.SpotlightAdapter
import br.com.digio.digiobank.presentation.utils.ProductsResult
import br.com.digio.digiobank.presentation.utils.hide
import br.com.digio.digiobank.presentation.utils.show
import br.com.digio.digiobank.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModel<MainViewModel>()
    private val productAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val spotlightAdapter: SpotlightAdapter by lazy { SpotlightAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        this.setupRecyclers()
        this.setupObservers()
        this.setupListeners()
    }

    private fun setupListeners() {
        binding.errorContainer.btnTryAgain.setOnClickListener {
            mainViewModel.getProducts()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            try {
                mainViewModel.getProducts()
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setupRecyclers() {
        binding.recyMainProducts.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = productAdapter
        }

        binding.recyMainSpotlight.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = spotlightAdapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mainViewModel.products.collect { result ->
                when (result) {
                    is ProductsResult.Loading -> handleLoading()
                    is ProductsResult.Error -> handleFailure()
                    is ProductsResult.Success -> {
                        handleSuccess(result.products)
                    }

                    else -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    private fun handleLoading() {
        binding.body.hide()
        binding.errorContainer.root.hide()
        binding.loadDigioContainer.root.show()
    }

    private fun handleSuccess(result: DigioProducts) {
        productAdapter.products = result.products
        spotlightAdapter.spotlights = result.spotlight

        binding.loadDigioContainer.root.hide()
        binding.body.show()
        setupDigioCashText()
    }

    private fun handleFailure() {
        val message = getString(R.string.error)

        binding.body.hide()
        binding.loadDigioContainer.root.hide()
        binding.errorContainer.root.show()
        binding.errorContainer.tvError.text = message
    }


    private fun setupDigioCashText() {
        val digioCacheText = getString(R.string.digio_cash)
        binding.txtMainDigioCash.text = SpannableString(digioCacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                START_SPAN_TEXT_INDEX,
                MIDDLE_SPAN_TEXT_INDEX,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                END_SPAN_TEXT_INDEX,
                digioCacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    companion object {
        private const val START_SPAN_TEXT_INDEX = 0
        private const val MIDDLE_SPAN_TEXT_INDEX = 5
        private const val END_SPAN_TEXT_INDEX = 6
    }
}