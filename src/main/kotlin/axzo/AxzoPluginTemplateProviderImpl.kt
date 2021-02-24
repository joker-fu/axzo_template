package axzo

import axzo.template.viewModelTemplate
import axzo.template.activityTemplate
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import axzo.template.fragmentTemplate
import axzo.template.itemTemplate
import axzo.template.listActivityTemplate
import axzo.template.listFragmentTemplate

class AxzoPluginTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
            activityTemplate,
            fragmentTemplate,
            listActivityTemplate,
            listFragmentTemplate,
            viewModelTemplate,
            itemTemplate
    )
}