package axzo.template.common

val systemName: String
    get() {
        val map = System.getenv()
        val user = map["USER"]
        val userName = map["USERNAME"]
        return user ?: userName ?: ""
    }