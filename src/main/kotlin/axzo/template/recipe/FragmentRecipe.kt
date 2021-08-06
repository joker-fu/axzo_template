package axzo.template.recipe

import axzo.template.common.*
import axzo.template.core.manager
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor


fun RecipeExecutor.fragmentRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        layoutName: String,
        packageName: String,
        useViewModel: Boolean = false,
        useDataBinding: Boolean = false,
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    val applicationPackage = projectData.applicationPackage ?: moduleData.packageName
    var isRegulatory = false
    if (applicationPackage == PackageManagement.REGULATORY) {
        isRegulatory = true
    }


    val activityFile = srcOut.resolve("${activityClass}Fragment.${ktOrJavaExt}")
    // 保存Activity
    save(moduleData.manager.fragment(applicationPackage,
            activityClass,
            layoutName,
            packageName,
            useViewModel,
            useDataBinding), activityFile)

    // 保存xml
    val xml = moduleData.manager.xml(activityClass, packageName, useViewModel, useDataBinding)
    save(xml, resOut.resolve("layout/${layoutName}.xml"))

    if (useViewModel) {
        // 保存ViewModel
        val viewModel = moduleData.manager.viewModel(packageName, activityClass)
        save(viewModel, srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
    }
    open(activityFile)

}