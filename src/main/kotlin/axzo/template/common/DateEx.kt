package axzo.template.common

import java.text.SimpleDateFormat
import java.util.*


val currentTime: String?
    get() = Date().dateToString()

/**根据日期获取指定格式时间
 * @return format yyyy-MM-dd HH:mm:ss
 */

fun Date.dateToString(format: String = "yyyy/MM/dd HH:mm"): String? {
    return try {
        SimpleDateFormat(format).format(this.time)
    } catch (e: Exception) {
        null
    }
}