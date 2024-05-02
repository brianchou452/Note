package cc.seaotter.note

import android.app.Application
import cc.seaotter.note.data.AppContainer
import cc.seaotter.note.data.AppDataContainer


class NoteApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
