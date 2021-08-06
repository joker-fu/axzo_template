package axzo.template.recipe

import axzo.template.core.manager
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.viewModelRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        packageName: String
) {
    val (projectData, srcOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    val viewModelFile = srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}")
    val viewModel = moduleData.manager.viewModel(packageName, activityClass)
    save(viewModel, viewModelFile)
    open(viewModelFile)
}