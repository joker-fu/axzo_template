package axzo.template

import axzo.template.recipe.fragmentRecipe
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File


val fragmentTemplate
    get() = template {
        name = "Axzo Fragment"
        description = "适用于 Axzo 的Fragment"
        minApi = MIN_API
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


        val activityClass = stringParameter {
            name = "Fragment Name"
            default = "Test"
            help = "请输入Fragment名称"
            constraints = listOf(Constraint.CLASS, Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        val userViewModel = booleanParameter {
            name = "Use ViewModel"
            default = false
            help = "是否使用ViewModel"
        }

        val useDataBinding = booleanParameter {
            name = "Use DataBinding"
            default = true
            help = "是否使用DataBinding"
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                fragmentToLayout(activityClass.value.toLowerCase())
            }
        }
        val packageName = defaultPackageNameParameter

        thumb {
            File("template_blank_activity.png")
        }
        widgets(
                TextFieldWidget(activityClass),
                TextFieldWidget(layoutName),
                CheckBoxWidget(userViewModel),
                CheckBoxWidget(useDataBinding),
                PackageNameWidget(packageName),
        )

        recipe = { data: TemplateData ->
            fragmentRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    layoutName.value,
                    packageName.value,
                    useViewModel = userViewModel.value,
                    useDataBinding = useDataBinding.value,)

        }
    }