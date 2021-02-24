package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName

fun generateListFragment(
        applicationPackage: String?,
        activityClass: String,
        packageName: String
) = """
package $packageName

import androidx.recyclerview.widget.RecyclerView
import ${applicationPackage}.R
import cn.axzo.ktx.ex.dp
import com.joker.core.recycler.BaseViewHolder
import com.joker.core.recycler.decoration.DividerItemDecoration
import com.joker.core.ui.base.list.BaseListFragment
import com.joker.core.ui.base.list.ItemTypeParams

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Fragment : BaseListFragment<Int>() {
    override fun createItemTypeParams(): ItemTypeParams {
        TODO()
//        return ItemTypeParams(R.layout.item_rating)
    }

//    override fun getEmptyImage(): Int? {
//        return R.mipmap.ic_launcher
//    }
//
//    override fun getEmptyText(): String? {
//        return null
//    }

    override fun loadData(pageIndex: Int) {
        addAll(listOf(1, 2, 3, 4, 5))
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? =
        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL, 25.dp)

    override fun itemBinder(holder: BaseViewHolder, item: Int?, position: Int) {

    }
}


"""