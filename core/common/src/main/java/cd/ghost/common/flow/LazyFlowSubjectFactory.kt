package cd.ghost.common.flow

interface LazyFlowSubjectFactory {

    /**
     * Create a new instance of [LazyFlowSubject]
     * @see DefaultLazyFlowSubjectFactory
     */
    fun <T> create(loader: ValueLoader<T>): LazyFlowSubject<T>

}