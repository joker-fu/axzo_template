package axzo.template.core

import com.android.tools.idea.wizard.template.ModuleTemplateData

/**
 *  author : czh
 *  date : 2021/6/3 14:49
 *  description :
 *  */
val ModuleTemplateData.manager: Code
    get() {
        val applicationPackage = this.projectTemplateData.applicationPackage ?: this.packageName
        return CodeFactory.create(applicationPackage)
    }