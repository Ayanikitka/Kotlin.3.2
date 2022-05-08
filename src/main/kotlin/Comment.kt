data class Comment(
    val id: Int = 0,
    val noteId: Int = 0,
    var message: String = "",
    var isDeleted: Boolean = false
)