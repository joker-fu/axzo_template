package axzo.template.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import axzo.template.fragment.res.layout.mvvmFragmentXml
import axzo.template.fragment.src.app_package.mvvmFragmentKt
import axzo.template.fragment.src.app_package.mvvmFragmentRepository
import axzo.template.fragment.src.app_package.mvvmFragmentViewModel


fun RecipeExecutor.mvvmFragmentRecipe(
        moduleData: ModuleTemplateData,
        fragmentClass: String,
        layoutName: String,
        packageName: String
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
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmFragment = mvvmFragmentKt(projectData.applicationPackage, fragmentClass, layoutName, packageName)
    // 保存fragment
    save(mvvmFragment, srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmFragmentXml(packageName, fragmentClass), resOut.resolve("layout/${layoutName}.xml"))
    // 保存viewmodel
    save(mvvmFragmentViewModel(packageName, fragmentClass), srcOut.resolve("${fragmentClass}ViewModel.${ktOrJavaExt}"))
    // 保存repository
    save(mvvmFragmentRepository(packageName, fragmentClass), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
}