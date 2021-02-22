package axzo.template.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import axzo.template.activity.xml.mvvmActivityXml
import axzo.template.activity.src.mvvmAcitivityKt
import axzo.template.activity.src.mvvmRepository
import axzo.template.activity.src.mvvmViewModel


fun RecipeExecutor.mvvmActivityRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    layoutName: String,
    packageName: String,
    useViewModel: Boolean,
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            activityClass = "${activityClass}Activity",
//            activityTitle = activityClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = false,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmActivity = mvvmAcitivityKt(projectData.applicationPackage, activityClass, layoutName, packageName, useViewModel)

    val activityFile = srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}")
    // 保存Activity
    save(mvvmActivity, activityFile)
    // 保存xml
    save(mvvmActivityXml(packageName, activityClass), resOut.resolve("layout/${layoutName}.xml"))
    if (useViewModel)
    // 保存viewmodel
        save(mvvmViewModel(packageName, activityClass), srcOut.resolve("${activityClass}ViewModel.${ktOrJavaExt}"))
    // 保存repository
    save(mvvmRepository(packageName, activityClass), srcOut.resolve("${activityClass}Repository.${ktOrJavaExt}"))

    open(activityFile)
}