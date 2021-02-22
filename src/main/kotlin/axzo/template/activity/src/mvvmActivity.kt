package axzo.template.activity.src

fun mvvmAcitivityKt(
    applicationPackage: String?,
    activityClass: String,
    layoutName: String,
    packageName: String,
    useViewModel: Boolean,
) = """
package ${packageName}

import android.app.Activity
import android.os.Bundle
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Activity${activityClass}Binding
import com.joker.core.ui.base.BaseDbActivity
import org.jetbrains.anko.startActivity
import androidx.activity.viewModels
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : 
 *  date : 
 *  description : 
 */
class ${activityClass}Activity : BaseDbActivity<Activity${activityClass}Binding>() {

    override val layout = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun onBindView(savedInstanceState: Bundle?) {
        ${
    if (useViewModel) """
    binding.apply {
       vm = viewModel
   }""" else ""
}

    companion object {
        fun start(act: Activity) {
            act.startActivity<${activityClass}Activity>()
        }
    }
}

"""