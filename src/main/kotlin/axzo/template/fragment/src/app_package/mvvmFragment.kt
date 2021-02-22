package axzo.template.fragment.src.app_package

fun mvvmFragmentKt(
        applicationPackage: String?,
        fragmentClass: String,
        layoutName: String,
        packageName: String
) = """
package ${packageName}
import android.os.Bundle
import com.shide.baselib.base.fragment.DataBindingConfig
import com.shide.baselib.base.fragment.LazyVmFragment
import ${applicationPackage}.R
class ${fragmentClass}Fragment : LazyVmFragment<${fragmentClass}ViewModel>() {

    override fun init(savedInstanceState: Bundle?) {
    
    }

    override fun lazyInit() {
    }

    override fun getLayoutId(): Int? {
        return R.layout.${layoutName}
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
    // exmaple    
    //        return DataBindingConfig(R.layout.fragment_item_report_form, viewModel)
    //            .addBindingParam(BR.vm, viewModel)
        return null
    }
    
    override fun initViewModel() {
        viewModel = getFragmentViewModel(${fragmentClass}ViewModel::class.java)
    }

}
"""