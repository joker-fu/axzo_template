package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName


fun generateFrg(applicationPackage: String?,
                activityClass: String,
                layoutName: String,
                packageName: String,
                useViewModel: Boolean,
                useDataBinding: Boolean): String {
    return if (useDataBinding) {
        generateDbFragment(applicationPackage, activityClass, layoutName, packageName, useViewModel)
    } else {
        generateFragment(applicationPackage, activityClass, layoutName, packageName, useViewModel)
    }
}


fun generateDbFragment(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.os.Bundle
import ${applicationPackage}.R
import android.view.View
import ${applicationPackage}.databinding.Fragment${activityClass}Binding
import com.joker.core.ui.base.BaseDbFragment
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Fragment : BaseDbFragment<Fragment${activityClass}Binding>() {

    override val layout = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun onBindView(view: View?, savedInstanceState: Bundle?) {
        ${
    if (useViewModel) """
        binding.apply {
           vm = viewModel
       }""" else ""
}
    }
}

"""

fun generateFragment(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.os.Bundle
import ${applicationPackage}.R
import android.view.View
import com.joker.core.ui.base.BaseFragment
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Fragment : BaseFragment() {

    override val layout = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun onBindView(view: View?, savedInstanceState: Bundle?) {
        
    }

}

"""