package br.com.digio.digiobank

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import br.com.digio.digiobank.presentation.ui.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

class MainActivityTest {

    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun onDisplayTitleShouldHaveHello() {
        launchActivity<MainActivity>().apply {
            val title = context.getString(R.string.hello_maria)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(title)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun onDisplayListSpotlightShouldRechargeItem() {
        server.dispatcher = MockServerUtil.dispatcherSuccess
        server.start(MockServerUtil.PORT)

        launchActivity<MainActivity>().apply {
            // TODO("validate if list displays items returned by server")
        }

        server.close()
    }
}