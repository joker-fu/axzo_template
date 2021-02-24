package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName


fun generateAct(applicationPackage: String?,
                activityClass: String,
                layoutName: String,
                packageName: String,
                useViewModel: Boolean,
                useDataBinding: Boolean): String {
    return if (useDataBinding) {
        generateDbActivity(applicationPackage, activityClass, layoutName, packageName, useViewModel)
    } else {
        generateActivity(applicationPackage, activityClass, layoutName, packageName, useViewModel)
    }
}


fun generateDbActivity(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.app.Activity
import android.os.Bundle
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Activity${activityClass}Binding
import com.joker.core.ui.base.BaseDbActivity
import org.jetbrains.anko.startActivity
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
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
    }

    companion object {
        fun start(act: Activity) {
            act.startActivity<${activityClass}Activity>()
        }
    }
}

"""

fun generateActivity(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.app.Activity
import android.os.Bundle
import ${applicationPackage}.R
import org.jetbrains.anko.startActivity
import com.joker.core.ui.base.BaseActivity
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Activity : BaseActivity() {

    override val layout = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun onBindView(savedInstanceState: Bundle?) {
    
    }

    companion object {
        fun start(act: Activity) {
            act.startActivity<${activityClass}Activity>()
        }
    }
}

"""