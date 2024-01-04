package cd.ghost.common.data_formatter

interface DateFormatter {

    fun format(date: Long?): String?

    fun getLong(date: String?): Long?

}