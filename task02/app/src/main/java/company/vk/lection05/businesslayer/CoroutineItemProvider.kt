package company.vk.lection05.businesslayer

import company.vk.lection05.datalayer.IItemAccessor2
import company.vk.lection05.objects.Item
import kotlinx.coroutines.*

class CoroutineItemProvider(private val accessor: IItemAccessor2) {
	private val scope = CoroutineScope(Dispatchers.Main)

	fun load(callback: (List<Item>) -> Unit) {
		scope.launch {
			try {
				val result = withContext(Dispatchers.IO) { accessor.items2() }
				callback(result)
			} catch (error: Throwable) {
				error.printStackTrace()
			}
		}
	}
}