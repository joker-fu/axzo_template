package axzo.template.core

import axzo.template.common.currentTime
import axzo.template.common.systemName

/**
 *  author : czh
 *  date : 2021/6/3 14:54
 *  description :
 *  */
class DefaultImpl : Code {
    override fun activity(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return """
package $packageName

import android.app.Activity
import android.os.Bundle
import ${applicationPackage}.R
${if (useDataBinding) "import ${applicationPackage}.databinding.Activity${activityClass}Binding" else ""}
${if (useDataBinding) "import com.joker.core.ui.base.BaseDbActivity" else "import com.joker.core.ui.base.BaseActivity"}
import org.jetbrains.anko.startActivity
${if (useViewModel) "import androidx.activity.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
${if (useDataBinding) "class ${activityClass}Activity : BaseDbActivity<Activity${activityClass}Binding>() {" else "class ${activityClass}Activity : BaseActivity() {"}
    override val layout = R.layout.${layoutName}

    ${if (useViewModel) "val viewModel by viewModels<${activityClass}ViewModel>()" else ""}

    override fun onBindView(savedInstanceState: Bundle?) {
        ${if (useViewModel) """
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

    }

    override fun fragment(applicationPackage: String, activityClass: String, layoutName: String, packageName: String, useViewModel: Boolean, useDataBinding: Boolean): String {
        return """
package $packageName

import android.os.Bundle
import ${applicationPackage}.R
import android.view.View
${if (useDataBinding) "import ${applicationPackage}.databinding.Fragment${activityClass}Binding" else ""}
${if (useDataBinding) "import com.joker.core.ui.base.BaseDbFragment" else "import com.joker.core.ui.base.BaseFragment"}
${if (useViewModel) "import androidx.fragment.app.viewModels" else ""}
${if (useViewModel) "import ${packageName}.viewmodel.${activityClass}ViewModel" else ""}

/**
 *  author : $systemName
 *  date : $currentTime
 *  description :
 */
${if (useDataBinding) "class ${activityClass}Fragment : BaseDbFragment<Fragment${activityClass}Binding>() {" else "class ${activityClass}Fragment : BaseFragment() {"}
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
    }

    override fun viewModel(packageName: String, activityClass: String): String {
        return """
package ${packageName}.viewmodel

import com.joker.core.viewmodel.BaseViewModel

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
        if (useDataBinding && useViewModel) {
            return viewModelXml(activityClass, packageName)
        }
        if (useDataBinding && !useViewModel) {
            return dataBindingXml()
        }
        return simpleXml()
    }

    override fun viewModelXml(activityClass: String, packageName: String) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
          <variable
            name="vm"
            type="${packageName}.viewmodel.${activityClass}ViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

    </LinearLayout>
</layout>
"""

    override fun dataBindingXml() = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

    </LinearLayout>
</layout>
"""

    override fun simpleXml() = """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

</LinearLayout>
"""
}