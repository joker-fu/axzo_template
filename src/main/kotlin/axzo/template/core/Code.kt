package axzo.template.core

/**
 *  author : czh
 *  date : 2021/6/3 14:51
 *  description :
 *  */
interface Code {

    fun activity(applicationPackage: String,
                 activityClass: String,
                 layoutName: String,
                 packageName: String,
                 useViewModel: Boolean,
                 useDataBinding: Boolean
    ): String

    fun fragment(applicationPackage: String,
                 activityClass: String,
                 layoutName: String,
                 packageName: String,
                 useViewModel: Boolean,
                 useDataBinding: Boolean
    ): String

    fun viewModel(packageName: String,
                  activityClass: String
    ): String

    fun xml(activityClass: String,
            packageName: String,
            useViewModel: Boolean,
            useDataBinding: Boolean
    ): String

    fun viewModelXml(activityClass: String,
                     packageName: String): String

    fun dataBindingXml(): String
    fun simpleXml(): String

}