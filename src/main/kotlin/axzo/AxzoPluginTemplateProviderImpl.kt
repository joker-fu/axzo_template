package axzo

import axzo.template.activity.ActivityTemplate
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import axzo.template.fragment.fragmentTemplate

class AxzoPluginTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
            ActivityTemplate,
            fragmentTemplate
    )
}