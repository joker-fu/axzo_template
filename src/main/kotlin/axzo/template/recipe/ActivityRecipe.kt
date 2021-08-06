package axzo.template.recipe

import axzo.template.core.manager
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest


fun RecipeExecutor.activityRecipe(
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
    generateManifest(
            moduleData = moduleData,
            activityClass = "${activityClass}Activity",
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )
    val act = moduleData.manager.activity(applicationPackage,
            activityClass,
            layoutName,
            packageName,
            useViewModel,
            useDataBinding)

    val activityFile = srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}")
    // 保存Activity
    save(act, activityFile)

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