package cd.ghost.common_impl

import android.content.Context
import cd.ghost.common.Resources

class AndroidResources constructor(
    private val context: Context
) : Resources {

    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getString(id: Int, vararg placeholders: Any): String {
        return context.getString(id, placeholders)
    }
}