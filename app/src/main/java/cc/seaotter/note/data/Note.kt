package cc.seaotter.note.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String = "",
    var content: String = "",
)