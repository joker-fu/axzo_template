package axzo.template

import axzo.template.recipe.listFragmentRecipe
import axzo.template.recipe.viewModelRecipe
import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
import java.io.File

val viewModelTemplate
    get() = template {
        revision = 1
        name = "Axzo ViewModel"
        description = "创建单个ViewModel"
        minApi = MIN_API
        minBuildApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


        val activityClass = stringParameter {
            name = "File Name"
            default = "Test"
            help = "请输入File名称 不需要输入ViewModel结尾"
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
            viewModelRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    packageName.value)
        }
    }