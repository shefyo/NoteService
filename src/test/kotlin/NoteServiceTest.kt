import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {
    class PostNotFoundException(message: String) : RuntimeException(message)
    @Test
    fun testAddNote() {
        val noteService = NoteService()
        val noteId = noteService.addNote("Заголовок", "Текст")
        assertEquals(1, noteId)
    }

    @Test
    fun testCreateComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        val commentId = noteService.createComment(1, Comment(1, 1, "Комментарий"))
        assertEquals(1, commentId)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testCreateCommentWithInvalidNoteId() {
        val noteService = NoteService()
        noteService.createComment(1, Comment(1, 1, "Комментарий"))
    }

    @Test
    fun testDeleteNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        val result = noteService.deleteNote(1)
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testDeleteNoteWithInvalidNoteId() {
        val noteService = NoteService()
        noteService.deleteNote(1)
    }

    @Test
    fun testDeleteComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, Comment(1, 1, "Комментарий"))
        val result = noteService.deleteComment(1)
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testDeleteCommentWithInvalidCommentId() {
        val noteService = NoteService()
        noteService.deleteComment(1)
    }

    @Test
    fun testUpdateNote() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        val result = noteService.update(1, "Новый заголовок", "Новый текст")
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testUpdateNoteWithInvalidNoteId() {
        val noteService = NoteService()
        noteService.update(1, "Новый заголовок", "Новый текст")
    }

    @Test
    fun testEditComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, Comment(1, 1, "Комментарий"))
        val result = noteService.editComment(1, 1, "Новый комментарий")
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testEditCommentWithInvalidCommentId() {
        val noteService = NoteService()
        noteService.editComment(1, 1, "Новый комментарий")
    }

    @Test
    fun testGetNoteById() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        val result = noteService.getNoteById(1)
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testGetNoteByIdWithInvalidNoteId() {
        val noteService = NoteService()
        noteService.getNoteById(1)
    }

    @Test
    fun testRestoreComment() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, Comment(1, 1, "Комментарий"))
        noteService.deleteComment(1)
        noteService.restoreComment(1)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testRestoreCommentWithInvalidCommentId() {
        val noteService = NoteService()
        noteService.restoreComment(1)
    }

    @Test
    fun testGetComments() {
        val noteService = NoteService()
        noteService.addNote("Заголовок", "Текст")
        noteService.createComment(1, Comment(1, 1, "Комментарий"))
        val result = noteService.getComments(1)
        assertEquals(1, result)
    }

    @Test(expected = NoteService.PostNotFoundException::class)
    fun testGetCommentsWithInvalidNoteId() {
        val noteService = NoteService()
        noteService.getComments(1)
    }

}