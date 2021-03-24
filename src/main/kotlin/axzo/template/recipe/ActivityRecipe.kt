package axzo.template.recipe

import axzo.template.common.*
import axzo.template.core.generateAct
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
        app2: Boolean = false,
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    val applicationPackage = projectData.applicationPackage ?: moduleData.packageName
    generateManifest(
            moduleData = moduleData,
            activityClass = "${activityClass}Activity",
            activityTitle = activityClass,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )

    val generateActivity = generateAct(applicationPackage, activityClass, layoutName, packageName, useViewModel, useDataBinding, app2)

    val activityFile = srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}")
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
    if (useViewModel) {
        // 保存ViewModel
        if (app2) {
            save(generateViewModel2(packageName, activityClass), srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
        } else {
            save(generateViewModel(packageName, activityClass), srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
        }
    }
    open(activityFile)

}