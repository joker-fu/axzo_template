package axzo.template.fragment.src.app_package


fun mvvmFragmentViewModel(
        packageName:String,
        fragmentClass:String
)="""
package ${packageName}
import androidx.lifecycle.viewModelScope
import com.shide.baselib.base.basemvvm.BaseViewModel
class ${fragmentClass}ViewModel : BaseViewModel() {
    private val repo by lazy { ${fragmentClass}Repository(this, viewModelScope, errorLiveData) }
}    
"""