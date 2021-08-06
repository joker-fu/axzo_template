package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName

/**
 *  author : czh
 *  date : 2021/6/3 15:13
 *  description :
 *  */
class RegulatoryImpl : Code {
    override fun activity(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return """
package $packageName

import android.os.Bundle
import ${applicationPackage}.R
import android.view.View
${if (useDataBinding) "import ${applicationPackage}.databinding.Activity${activityClass}Binding" else ""}
${if (useDataBinding) "import cn.axzo.regulatory.common.base.AxDbActivity" else "import cn.axzo.regulatory.common.base.AxActivity"}
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
${if (useDataBinding) "class ${activityClass}Activity : AxDbActivity<Activity${activityClass}Binding>(R.layout.${layoutName}) {" else "class ${activityClass}Activity : AxActivity(R.layout.${layoutName}) {"}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun onBindView(savedInstanceState: Bundle?, currentView: View?) {
        ${
            if (useViewModel) """
        binding.apply {
           vm = viewModel
       }""" else ""
        }
    }

    override fun onBindData() {

    }

    override fun onObserve() {

    }
}

"""
    }

    override fun fragment(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return """
package $packageName

import android.os.Bundle
import ${applicationPackage}.R
import android.view.View
${if (useDataBinding) "import ${applicationPackage}.databinding.Fragment${activityClass}Binding" else ""}
${if (useDataBinding) "import cn.axzo.regulatory.common.base.AxDbFragment" else "import cn.axzo.regulatory.common.base.AxFragment"}
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
${if (useDataBinding) "class ${activityClass}Fragment : AxDbFragment<Fragment${activityClass}Binding>(R.layout.${layoutName}) {" else "class ${activityClass}Fragment : AxFragment(R.layout.${layoutName}) {"}
    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun onBindView(savedInstanceState: Bundle?, currentView: View?) {
        ${
            if (useViewModel) """
        binding.apply {
           vm = viewModel
       }""" else ""
        }
    }
    
    override fun onBindData() {

    }

    override fun onObserve() {

    }
}

"""
    }

    override fun viewModel(packageName: String, activityClass: String) = """
package ${packageName}.viewmodel

import cn.axzo.regulatory.common.base.AxViewModel

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
class ${activityClass}ViewModel : AxViewModel() {

}
"""

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

