package axzo.template.activity

import axzo.template.activity.src.generateAct
import axzo.template.common.generateViewModel
import axzo.template.common.generateActivityXml
import axzo.template.common.generateDbXml
import axzo.template.common.generateViewModelXml
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
            activityTitle = activityClass,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )

    val generateActivity = generateAct(applicationPackage, activityClass, layoutName, packageName, useViewModel, useDataBinding)

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
        save(generateViewModel(packageName, activityClass), srcOut.resolve("viewmodel/${activityClass}ViewModel.${ktOrJavaExt}"))
    }
    open(activityFile)

}