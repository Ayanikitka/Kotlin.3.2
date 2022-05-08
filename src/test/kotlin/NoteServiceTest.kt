import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0

        val id1 = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val id2 = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        assertTrue(id1 != id2)
    }

    @Test
    fun createComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0

        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId1 = NoteService.createComment(noteId, "")
        val commentId2 = NoteService.createComment(noteId, "")

        assertTrue(commentId1 != commentId2)
    }

    @Test
    fun createComment_withNoNote() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0

        NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(-15, "")

        assertEquals(commentId, -1)
    }

    @Test
    fun delete() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val isDeleted = NoteService.delete(noteId)

        assertEquals(isDeleted, 1)
    }

    @Test
    fun delete_isDeletedNote() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy,
        )

        NoteService.delete(noteId)
        val isDeleted = NoteService.delete(noteId)

        assertEquals(isDeleted, -1)
    }

    @Test
    fun deleteComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        val isDeleted = NoteService.deleteComment(noteId, commentId)

        assertEquals(isDeleted, 1)
    }

    @Test
    fun deleteComment_isDeletedComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        NoteService.deleteComment(noteId, commentId)
        val isDeleted = NoteService.deleteComment(noteId, commentId)

        assertEquals(isDeleted, -1)
    }

    @Test
    fun edit() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val isEdit = NoteService.edit(
            noteId,
            title,
            text,
            privacy,
            commentPrivacy
        )

        assertEquals(isEdit, 1)
    }

    @Test
    fun edit_withNoNote() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val isEdit = NoteService.edit(
            -15,
            title,
            text,
            privacy,
            commentPrivacy
        )

        assertEquals(isEdit, -1)
    }

    @Test
    fun editComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        val isEdit = NoteService.editComment(commentId, noteId, "")

        assertEquals(isEdit, 1)
    }

    @Test
    fun editComment_withNoNotes() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        val isEdit = NoteService.editComment(commentId, -15, "")

        assertEquals(isEdit, -1)
    }

    @Test
    fun editComment_withNoComments() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val isEdit = NoteService.editComment(-15, noteId, "")

        assertEquals(isEdit, -1)
    }

    @Test
    fun get() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId1 = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val noteId2 = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val noteIds = "$noteId1 $noteId2"
        val notes = NoteService.get(noteIds)

        assertNotNull(notes)
    }

    @Test
    fun getById() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val getNoteId = NoteService.getById(noteId)

        assertNotNull(getNoteId)
    }

    @Test
    fun getById_noNotes() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0

        val getNoteId = NoteService.getById(-15)

        assertNull(getNoteId)
    }

    @Test
    fun getComments() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        NoteService.createComment(noteId, "")

        val getComments = NoteService.getComments(noteId)

        assertNotNull(getComments)
    }

    @Test
    fun getComments_noComments() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val getComments = NoteService.getComments(noteId)

        assertNull(getComments)
    }

    @Test
    fun getComments_noNotes() {
        NoteService.clearNotes()

        val getComments = NoteService.getComments(-15)

        assertNull(getComments)
    }

    @Test
    fun restoreComments() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        NoteService.deleteComment(noteId, commentId)
        val isRestored = NoteService.restoreComments(noteId, commentId)

        assertEquals(isRestored, 1)
    }

    @Test
    fun restoreComments_notDeletedComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )
        val commentId = NoteService.createComment(noteId, "")

        val isRestored = NoteService.restoreComments(noteId, commentId)

        assertEquals(isRestored, -1)
    }

    @Test
    fun restoreComments_noNote() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0

        val isRestored = NoteService.restoreComments(-15, -15)

        assertEquals(isRestored, -1)
    }

    @Test
    fun restoreComments_noComment() {
        NoteService.clearNotes()
        val title = ""
        val text = ""
        val privacy = 0
        val commentPrivacy = 0
        val noteId = NoteService.add(
            title,
            text,
            privacy,
            commentPrivacy
        )

        val isRestored = NoteService.restoreComments(noteId, -15)

        assertEquals(isRestored, -1)
    }
}