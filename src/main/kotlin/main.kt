data class Note(
    val id: Int,
    val userId: Int,
    val title: String,
    val text: String
)

data class Comment(
    val id: Int,
    val noteId: Int,
    val text: String
)

class NoteService {
    private val notes: MutableList<Note> = mutableListOf()
    private val comments: MutableList<Comment> = mutableListOf()
    private val deletedComments: MutableList<Comment> = mutableListOf()
    private val deletedNotes: MutableList<Note> = mutableListOf()
    private var nextNoteId = 1
    private var nextUserId = 1

    fun addNote(title: String, text: String): Int {
        val note = Note(nextNoteId,nextUserId, title, text)
        notes.add(note)
        println("Заметка была успешно создана. ID созданной заметки: $nextNoteId")
        return nextNoteId++
    }

    class PostNotFoundException(message: String) : RuntimeException(message)

    fun createComment(noteId: Int, comment: Comment): Int {
        val note = notes.find { it.id == noteId }
        if (note != null) {
            val newComment = Comment(comment.id, noteId, comment.text)
            comments.add(newComment)
            println("Комментарий был добавлен к посту с ID: $noteId. ID созданного комментария: ${newComment.id}.")
            return newComment.id
        } else {
            throw PostNotFoundException("Пост с ID $noteId не найден.")
        }
    }

    fun deleteNote(noteId: Int): Int {
        val note = notes.find { it.id == noteId }
        val comment = comments.find { it.noteId == noteId }
        if (note != null && notes.contains(note)) {
            notes.remove(note)
            deletedNotes.add(note)
            if (comment != null) {
                comments.remove(comment)
                deletedComments.add(comment)
            }
            println("Заметка с ID ${note.id} была удалена.")
            return 1
        } else {
            throw PostNotFoundException("Заметка не найдена.")
        }
    }

    fun deleteComment(id: Int): Int {
        val comment = comments.find { it.id == id }
        if (comment != null && comments.contains(comment)) {
            comments.remove(comment)
            deletedComments.add(comment)
            println("Комментарий с ID ${comment.id} был удален.")
            return 1
        } else {
            throw PostNotFoundException("Комментарий не найден.")
        }
    }

    fun update(noteId: Int, title: String, text: String): Int {
        val note = notes.find { it.id == noteId }
        if (note != null && notes.contains(note)) {
            val newNote = Note(noteId, nextUserId, title, text)
            notes.remove(note)
            notes.add(newNote)
            println("Заметка была изменена")
            return 1
        } else {
            throw PostNotFoundException("Заметка не найдена.")
        }
    }

    fun editComment(id: Int, noteId: Int, text: String): Int {
        val comment = comments.find { it.id == id }
        if (comment != null && comments.contains(comment)) {
            val newComment = Comment(id, noteId, text)
            comments.remove(comment)
            comments.add(newComment)
            println("Комментарий был изменен")
            return 1
        } else {
            throw PostNotFoundException("Комментарий не найден.")
        }
    }

    fun getNoteById(noteId: Int): Int {
        val note = notes.find { it.id == noteId }
        if (note != null) {
            println(note)
            return 1
        } else {
            throw PostNotFoundException("Заметка не найдена.")
        }
    }

    fun restoreComment(id: Int) {
        val comment = deletedComments.find { it.id == id }
        if (deletedComments.contains(comment)) {
            if (comment != null) {
                comments.add(comment)
                deletedComments.remove(comment)
                println("Комментарий восстановлен.")
            }
        } else {
            throw PostNotFoundException("Комментарий не найден.")
        }
    }

    fun getComments(noteId: Int): Int {
        val comment = comments.find { it.noteId == noteId }
        if (comment != null) {
            println(comment)
            return 1
        } else {
            throw PostNotFoundException("Комментарий не найден.")
        }
    }
    fun getNotesByUser(userId: Int): Int {
        val note = notes.find { it.userId == userId }
        if (note != null) {
            println(note)
            return 1
        } else {
            throw PostNotFoundException("Комментарий не найден.")
        }
    }
}