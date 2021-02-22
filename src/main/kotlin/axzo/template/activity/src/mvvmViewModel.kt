package axzo.template.activity.src


fun mvvmViewModel(
        packageName:String,
        activityClass:String
)="""
package ${packageName}
import androidx.lifecycle.viewModelScope
import com.shide.baselib.base.basemvvm.BaseViewModel
class ${activityClass}ViewModel : BaseViewModel() {
    private val repo by lazy { ${activityClass}Repository(this, viewModelScope, errorLiveData) }
}    
"""