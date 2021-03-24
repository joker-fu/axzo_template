package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName


fun generateFrg(applicationPackage: String?,
                activityClass: String,
                layoutName: String,
                packageName: String,
                useViewModel: Boolean,
                useDataBinding: Boolean,
                app2: Boolean): String {

    return when {
        useDataBinding && app2 -> {
            generateDbFragment2(applicationPackage, activityClass, layoutName, packageName, useViewModel)
        }
        useDataBinding && !app2 -> {
            generateDbFragment(applicationPackage, activityClass, layoutName, packageName, useViewModel)
        }
        !useDataBinding && app2 -> {
            generateFragment2(applicationPackage, activityClass, layoutName, packageName, useViewModel)
        }
        !useDataBinding && !app2 -> {
            generateFragment(applicationPackage, activityClass, layoutName, packageName, useViewModel)
        }
        else -> ""
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

fun generateDbFragment2(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.os.Bundle
import android.view.View
import ${applicationPackage}.R
import cn.axzo.regulatory.common.base.AxDbFragment
import ${applicationPackage}.databinding.Fragment${activityClass}Binding
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Fragment : AxDbFragment<Fragment${activityClass}Binding>(R.layout.${layoutName}) {

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun onBindView(savedInstanceState: Bundle?, currentView: View?) {

    }

    override fun onBindData() {

    }

    override fun onObserve() {

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

fun generateFragment2(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean
) = """
package $packageName

import android.os.Bundle
import android.view.View
import ${applicationPackage}.R
import cn.axzo.regulatory.common.base.AxFragment
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}Fragment : AxFragment(R.layout.${layoutName}) {

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun onBindView(savedInstanceState: Bundle?, currentView: View?) {

    }

    override fun onBindData() {

    }

    override fun onObserve() {

    }
}

"""