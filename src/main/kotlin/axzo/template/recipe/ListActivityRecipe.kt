package axzo.template.recipe

import axzo.template.common.PackageManagement
import axzo.template.core.generateListActivity
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest


fun RecipeExecutor.listActivityRecipe(
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
    generateManifest(
            moduleData = moduleData,
            activityClass = "${activityClass}Activity",
            activityTitle = activityClass,
            packageName = packageName,
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
    )

    if (isRegulatory) {
        return
    }

    val generateActivity = generateListActivity(applicationPackage, activityClass, packageName)

    val activityFile = srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}")
    // 保存Activity
    save(generateActivity, activityFile)
    open(activityFile)

}