package axzo

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import axzo.template.activity.mvvmActivityTemplate

class AxzoPluginTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
            mvvmActivityTemplate
    )
}