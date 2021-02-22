package axzo.template.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val mvvmActivityTemplate
    get() = template {
        revision = 1
        name = "Axzo Activity"
        description = "适用于 Axzo 的Activity"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "MainActivity"
            help = "请输入Activity名称"
            constraints = listOf(Constraint.CLASS, Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        val userViewModel = booleanParameter {
            name = "Use ViewModel"
            default = true
            help = "是否使用ViewModel"
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                val name = activityClass.value.replaceFirst("Activity", "")
                activityToLayout(camelCaseToUnderlines(name).toLowerCase())
            }
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(activityClass),
            CheckBoxWidget(userViewModel),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName)
        )

        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value.replaceFirst("Activity", ""),
                layoutName.value,
                packageName.value,
                userViewModel.value)
        }
    }


val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = ""
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }