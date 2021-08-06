package axzo.template.recipe

import axzo.template.common.PackageManagement
import axzo.template.core.generateListFragment
import axzo.template.core.generateListFragment2
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor


fun RecipeExecutor.listFragmentRecipe(
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

    val generateActivity = if (isRegulatory) {
        generateListFragment2(activityClass, packageName)
    } else {
        generateListFragment(applicationPackage, activityClass, packageName)
    }
    val activityFile = srcOut.resolve("${activityClass}Fragment.${ktOrJavaExt}")
    // 保存Activity
    save(generateActivity, activityFile)
    open(activityFile)
}