package navigationcomponentturtorialcom.example.inventoryapp.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import navigationcomponentturtorialcom.example.inventoryapp.database.Word
import navigationcomponentturtorialcom.example.inventoryapp.database.WordDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed
    val allWords: Flow<List<Word>> = wordDao.getAlphabetzedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    /* Mặc dù, Room chạy các truy vấn suspend ngoài luồng chính (main thread), do đó chúng ta
     * không cần phải thực hiện bất kỳ điều gì khác để đảm bảo rằng chúng ta không thực hiện
     * công việc cơ sở dữ liệu chạy lâu trên luồng chính. */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}