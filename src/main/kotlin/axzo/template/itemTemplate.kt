package axzo.template

import axzo.template.recipe.activityRecipe
import axzo.template.recipe.itemRecipe
import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.booleanParameter
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
import java.io.File

val itemTemplate
    get() = template {
        revision = 1
        name = "Axzo Item XML"
        description = "创建一个xml"
        minApi = MIN_API
        minBuildApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)




        val useDataBinding = booleanParameter {
            name = "Use DataBinding"
            default = true
            help = "是否使用DataBinding"
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "item_"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
        }

        val packageName = defaultPackageNameParameter

        thumb {
            File("template_blank_activity.png")
        }

        widgets(
                TextFieldWidget(layoutName),
                CheckBoxWidget(useDataBinding),
                PackageNameWidget(packageName)
        )

        recipe = { data: TemplateData ->
            itemRecipe(
                    data as ModuleTemplateData,
                    layoutName.value,
                    useDataBinding = useDataBinding.value)

        }
    }