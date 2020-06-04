package will.shiro.desafiopicpay.view.user.list

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import will.shiro.desafiopicpay.R
import will.shiro.desafiopicpay.view.MainActivity

@RunWith(JUnit4::class)
class ContactListFragmentTest {

    //    @get:Rule
//    val activityRule = ActivityTestRule(MainActivity::class.java)
//
    @get:Rule
    val taskExecutorRule = CountingTaskExecutorRule()

    @Before
    fun a() {
        val a = IdlingRegistry.getInstance()
        a.register(EspressoIdlingResource.coutingIdlingResource)
    }

    @Test
    fun check_list() {
//        launchFragmentInContainer<ContactListFragment>(
//        ).onFragment {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.contact_list_recycler_view)).check(matches(isDisplayed()))
//            onView(withId(it.binding.contactListRecyclerView.id)).check(matches(isDisplayed()))
//            assert(it.binding.contactListRecyclerView.childCount > 0)
//            IdlingRegistry.getInstance().unregister(it.countlingIdlingResource)
    }
}