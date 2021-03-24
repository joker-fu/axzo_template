package axzo.template.recipe

import axzo.template.common.*
import axzo.template.core.generateFrg
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

    val generateActivity = generateFrg(applicationPackage,
            activityClass,
            layoutName,
            packageName,
            useViewModel,
            useDataBinding,
            isRegulatory)

    val activityFile = srcOut.resolve("${activityClass}Fragment.${ktOrJavaExt}")
    // 保存Activity
    save(generateActivity, activityFile)

    // 保存xml
    if (useViewModel && useDataBinding) {
        save(generateViewModelXml(packageName, activityClass), resOut.resolve("layout/${layoutName}.xml"))
    } else if (!useViewModel && useDataBinding) {
        save(generateDbXml(), resOut.resolve("layout/${layoutName}.xml"))
    } else {
        save(generateActivityXml(), resOut.resolve("layout/${layoutName}.xml"))
    }
    if (useViewModel && !isRegulatory) {
        // 保存ViewModel
        save(generateViewModel(packageName, activityClass), srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
    }
    if (useViewModel && isRegulatory) {
        // 保存ViewModel
        save(generateViewModel2(packageName, activityClass), srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
    }
    open(activityFile)

}