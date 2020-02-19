package will.shiro.domain.interactor.user

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.util.throwable.TransactionRejectedThrowable
import java.lang.RuntimeException

class CreateTransactionTest {

    private lateinit var mockedUserRepository: UserRepository
    private lateinit var mockedTransactionRequest: TransactionRequest
    private lateinit var createTransaction: CreateTransaction
    private lateinit var mockedTransaction: Transaction

    @Before
    fun setup() {
        mockedUserRepository = mockk()
        mockedTransactionRequest = mockk()
        createTransaction = CreateTransaction(mockedUserRepository)
        mockedTransaction = mockk()
    }

    @Test
    fun `transaction success`() {
        every {
            mockedUserRepository.createPayment(mockedTransactionRequest)
        } returns Single.just(
            mockedTransaction
        )
        every { mockedTransaction.success } returns true

        assertEquals(
            createTransaction.execute(mockedTransactionRequest).blockingGet(),
            mockedTransaction
        )
    }

    @Test
    fun `transaction failure`() {
        every {
            mockedUserRepository.createPayment(mockedTransactionRequest)
        } returns Single.just(
            mockedTransaction
        )
        every { mockedTransaction.success } returns false

        val throwable = assertThrows<RuntimeException> {
            createTransaction.execute(mockedTransactionRequest).blockingGet()
        }
        assertEquals(
            "Transaction has been rejected",
            throwable.cause?.message
        )
    }
}