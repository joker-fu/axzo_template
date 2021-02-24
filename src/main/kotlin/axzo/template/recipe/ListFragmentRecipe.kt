package axzo.template.recipe

import axzo.template.core.generateListFragment
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
    val generateActivity = generateListFragment(applicationPackage, activityClass, packageName)

    val activityFile = srcOut.resolve("${activityClass}Fragment.${ktOrJavaExt}")
    // 保存Activity
    save(generateActivity, activityFile)
    open(activityFile)
}