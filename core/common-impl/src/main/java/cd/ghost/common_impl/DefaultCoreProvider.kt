package cd.ghost.common_impl

import android.content.Context
import cd.ghost.common.AppRestarter
import cd.ghost.common.CoreProvider
import cd.ghost.common.Resources
import kotlinx.coroutines.CoroutineScope

class DefaultCoreProvider(
    context: Context,
    override val appRestarter: AppRestarter,
    override val resources: Resources = AndroidResources(context),
    override val globalScope: CoroutineScope = createDefaultGlobalScope(),
) : CoreProvider