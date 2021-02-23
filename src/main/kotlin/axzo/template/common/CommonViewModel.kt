package axzo.template.common

import axzo.template.common.currentTime


fun generateViewModel(
        packageName:String,
        activityClass:String
)="""
package ${packageName}.viewmodel

import com.joker.core.viewmodel.BaseViewModel

/**
 *  author : 
 *  date : $currentTime
 *  description : 
 */
class ${activityClass}ViewModel : BaseViewModel() {

}
"""