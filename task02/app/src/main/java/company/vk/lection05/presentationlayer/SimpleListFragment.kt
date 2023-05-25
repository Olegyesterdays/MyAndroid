package company.vk.lection05.presentationlayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.vk.lection05.R
import company.vk.lection05.businesslayer.CoroutineItemProvider
import company.vk.lection05.datalayer.IItemAccessor2
import company.vk.lection05.datalayer.RetrofitProvider
import company.vk.lection05.presentationlayer.adapters.ItemAdapter

open class SimpleListFragment : Fragment() {
    private val provider by lazy { initializeProvider() }
    private val itemAdapter = ItemAdapter()

    private val accessor = RetrofitProvider().provide().create(IItemAccessor2::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
            adapter = itemAdapter
        }

        provider.load {
            Log.d("TECH", "submit list with size=${it.size}")
            itemAdapter.submitList(it)
        }
    }

    private fun initializeProvider(): CoroutineItemProvider {
        return CoroutineItemProvider(accessor)
    }

    companion object {
        protected const val COLUMN_COUNT = 3

        fun newInstance(): SimpleListFragment {
            return SimpleListFragment()
        }
    }
}