package cd.ghost.common.data_formatter

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class AndroidDateFormatter @Inject constructor() : DateFormatter {

    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormatter = SimpleDateFormat("E.yyyy")

    override fun format(date: Long?): String? {
        if (date == null) return null
        val value = try {
            simpleDateFormatter.format(Date(date))
        } catch (e: Exception) {
            null
        }
        return value
    }

    override fun getLong(date: String?): Long? {
        if (date == null) return null
        val value = try {
            simpleDateFormatter.parse(date)?.time
        } catch (e: Exception) {
            null
        }
        return value
    }

}