package axzo.template.recipe

import axzo.template.common.PackageManagement
import axzo.template.common.generateViewModel
import axzo.template.common.generateViewModel2
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.viewModelRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        packageName: String
) {
    val (projectData, srcOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    val applicationPackage = projectData.applicationPackage ?: moduleData.packageName
    var isRegulatory = false
    if (applicationPackage == PackageManagement.REGULATORY) {
        isRegulatory = true
    }

    val viewModelFile = srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}")
    if (isRegulatory) {
        save(generateViewModel2(packageName, activityClass), viewModelFile)
    } else {
        save(generateViewModel(packageName, activityClass), viewModelFile)
    }
    open(viewModelFile)
}