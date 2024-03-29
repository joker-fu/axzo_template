package axzo.template

import axzo.template.recipe.listActivityRecipe
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File


val listActivityTemplate
    get() = template {
        name = "Axzo ListActivity"
        description = "适用于 Axzo 的ListActivity"
        minApi = MIN_API
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


        val activityClass = stringParameter {
            name = "Activity Name"
            default = "Test"
            help = "请输入Activity名称"
            constraints = listOf(Constraint.CLASS, Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        val packageName = defaultPackageNameParameter

        thumb {
            File("template_blank_activity.png")
        }

        widgets(
                TextFieldWidget(activityClass),
                PackageNameWidget(packageName)
        )

        recipe = { data: TemplateData ->
            listActivityRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    packageName.value)
        }
    }