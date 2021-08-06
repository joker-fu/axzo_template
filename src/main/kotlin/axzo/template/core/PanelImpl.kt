package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName

/**
 *  author : czh
 *  date : 2021/6/3 15:15
 *  description :
 *  */
class PanelImpl : Code {
    override fun activity(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return if (useDataBinding) {
            """
package $packageName

import android.app.Activity
import ${applicationPackage}.R
import ${applicationPackage}.databinding.Activity${activityClass}Binding
import lib.comm.base.BaseDBActivity
import org.jetbrains.anko.startActivity
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
class ${activityClass}Activity : BaseDBActivity<Activity${activityClass}Binding>() {

    override fun getPageName() = ""

    override fun getLayoutId() = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}


    override fun initViews() {
    	   super.initViews()
        ${
                if (useViewModel) """
        dataBinding.apply {
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

        } else {
            """
package $packageName

import android.app.Activity
import ${applicationPackage}.R
import org.jetbrains.anko.startActivity
import lib.comm.base.BaseActivity
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
class ${activityClass}Activity : BaseActivity() {

    override fun getPageName() = ""

    override fun getLayoutId() = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun initViews() {
    	   super.initViews()
    
    }

    companion object {
        fun start(act: Activity) {
            act.startActivity<${activityClass}Activity>()
        }
    }
}

"""
        }
    }

    override fun fragment(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return """
package $packageName

import ${applicationPackage}.R
${if (useDataBinding) "import ${applicationPackage}.databinding.Fragment${activityClass}Binding" else ""}
${if (useDataBinding) "import lib.comm.base.BaseDBFragment" else "import lib.comm.base.BaseFragment"}
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
${if (useDataBinding) "class ${activityClass}Fragment : BaseDBFragment<Fragment${activityClass}Binding>() {" else "class ${activityClass}Fragment : BaseFragment() {"}
    override fun getPageName() = ""

    override fun getLayoutId() = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun initViews() {
        super.initViews()
        ${
            if (useViewModel) """
        binding.apply {
           vm = viewModel
       }""" else ""
        }
    }
}

"""
    }

    override fun viewModel(packageName: String, activityClass: String): String {
        return """
package ${packageName}.viewmodel

import lib.comm.base.BaseViewModel

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
class ${activityClass}ViewModel : BaseViewModel() {

}
"""
    }

    override fun xml(activityClass: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return DefaultImpl().xml(activityClass, packageName, useViewModel, useDataBinding)
    }

    override fun viewModelXml(activityClass: String, packageName: String): String {
        return DefaultImpl().viewModelXml(activityClass, packageName)
    }

    override fun dataBindingXml(): String {
        return DefaultImpl().dataBindingXml()
    }

    override fun simpleXml(): String {
        return DefaultImpl().simpleXml()
    }
}