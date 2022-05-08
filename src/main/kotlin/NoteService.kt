object NoteService {

    private var notes = mutableListOf<Note>()
    private var nextNoteId = 1

    fun add(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
    ): Int {
        val note = Note(
            nextNoteId,
            title,
            text,
            privacy,
            commentPrivacy
        )
        nextNoteId++
        notes.add(note)
        return note.id
    }

    fun createComment(
        noteId: Int,
        message: String
    ): Int {
        for (note in notes) {
            if (note.id == noteId) {
                val commentId = note.comments.size
                val comment = Comment(
                    commentId,
                    noteId
                )
                note.comments.add(comment)
                return commentId
            }
        }
        return -1
    }

    fun delete(
        noteId: Int
    ): Int {
        for (note in notes) {
            if (note.id == noteId && !note.isDeleted) {
                note.isDeleted = true
                return 1
            }
        }
        return -1
    }

    fun deleteComment(
        noteId: Int,
        commentId: Int
    ): Int {
        for (note in notes) {
            if (note.id == noteId)
                for (comment in note.comments) {
                    if (comment.id == commentId && !comment.isDeleted) {
                        comment.isDeleted = true
                        return 1
                    }
                }
        }
        return -1
    }

    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
    ): Int {
        for (note in notes) {
            if (note.id == noteId && !note.isDeleted) {
                note.title = title
                note.text = text
                note.privacy = privacy
                note.commentPrivacy = commentPrivacy
                return 1
            }
        }
        return -1
    }

    fun editComment(
        commentId: Int,
        noteId: Int,
        message: String
    ): Int {
        for (note in notes) {
            if (note.id == noteId)
                for (comment in note.comments) {
                    if (comment.id == commentId) {
                        comment.message = message
                        return 1
                    }
                }
        }
        return -1
    }

    fun get(
        noteIds: String //номера заметок через пробел
    ): MutableList<Note> {
        val indexes = noteIds.split(" ")
        val currentNotes = mutableListOf<Note>()
        for (index in indexes) {
            for (note in notes) {
                if (index.toInt() == note.id) {
                    currentNotes.add(note)
                }
            }
        }
        return currentNotes
    }

    fun getById(
        noteId: Int
    ): Note? {
        for (note in notes) {
            if (note.id == noteId) return note
        }
        return null
    }

    fun getComments(
        noteId: Int
    ): MutableList<Comment>? {
        for (note in notes) {
            if (note.id == noteId && note.comments.size != 0) return note.comments
        }
        return null
    }

    fun restoreComments(
        noteId: Int,
        commentId: Int
    ): Int {
        for (note in notes) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    if (comment.id == commentId) {
                        return if (comment.isDeleted) {
                            comment.isDeleted = false
                            1
                        } else {
                            -1
                        }
                    }
                }
            }
        }
        return -1
    }

    fun clearNotes() : Unit {
        notes = mutableListOf()
    }
}