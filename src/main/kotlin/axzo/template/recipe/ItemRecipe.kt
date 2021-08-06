package axzo.template.recipe

import axzo.template.common.generateActivityXml
import axzo.template.common.generateDbXml
import axzo.template.core.manager
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.itemRecipe(
        moduleData: ModuleTemplateData,
        layoutName: String,
        useDataBinding: Boolean = true
) {
    val resOut = moduleData.resDir
    val xmlFile = resOut.resolve("layout/${layoutName}.xml")
    val xml = moduleData.manager.xml("", "", false, useDataBinding)
    save(xml, xmlFile)
    open(xmlFile)
}