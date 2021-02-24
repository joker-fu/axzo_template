package axzo.template.common

fun generateViewModel(
        packageName:String,
        activityClass:String
)="""
package ${packageName}.viewmodel

import com.joker.core.viewmodel.BaseViewModel

/**
 *  author : $systemName
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}ViewModel : BaseViewModel() {

}
"""