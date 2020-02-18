package will.shiro.domain.util.throwable

class NoItemFoundLocalThrowable : Throwable("No item found in local database")
class TransactionRejectedThrowable: Throwable("Transaction has been rejected")