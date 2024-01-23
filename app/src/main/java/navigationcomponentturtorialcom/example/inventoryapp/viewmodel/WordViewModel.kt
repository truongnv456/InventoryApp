package navigationcomponentturtorialcom.example.inventoryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import navigationcomponentturtorialcom.example.inventoryapp.database.Word
import navigationcomponentturtorialcom.example.inventoryapp.repository.WordRepository
import kotlinx.coroutines.*

class WordViewModel(private val wordRepository: WordRepository): ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>> = wordRepository.allWords.asLiveData()

    /* Launching a new coroutine to insert data in a non-blocking way*/
    fun insert(word: Word) = CoroutineScope(Dispatchers.Default).launch {
        wordRepository.insert(word)
    }
}