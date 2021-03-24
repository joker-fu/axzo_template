package axzo.template.recipe

import axzo.template.common.generateViewModel
import axzo.template.common.generateViewModel2
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.viewModelRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        packageName: String,
        app2: Boolean
) {
    val (projectData, srcOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    val viewModelFile = srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}")
    if (app2) {
        save(generateViewModel2(packageName, activityClass), viewModelFile)
    } else {
        save(generateViewModel(packageName, activityClass), viewModelFile)
    }
    open(viewModelFile)
}