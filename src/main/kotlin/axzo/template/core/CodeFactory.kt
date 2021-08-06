package axzo.template.core

/**
 *  author : czh
 *  date : 2021/6/3 14:51
 *  description :
 *  */
abstract class CodeFactory : Code {
    companion object {
        /**
         * 根据设备型号创建相应的HardwareIO实例
         */
        fun create(applicationPackage: String): Code {
            return when (applicationPackage) {
                "cn.axzo.panel" -> PanelImpl()
                "cn.axzo.regulatory" -> RegulatoryImpl()
                else -> {
                    DefaultImpl()
                }
            }
        }
    }
}