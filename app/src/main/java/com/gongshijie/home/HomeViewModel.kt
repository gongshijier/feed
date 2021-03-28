package com.gongshijie.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gongshijie.feed.api.NewsCell
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {
    lateinit var newsRepository: NewsRepository
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var data = MutableLiveData<List<NewsCell>>()


    suspend fun saveNews(list: List<NewsCell>) {
        newsRepository.saveNews(list)
    }

    private suspend fun asyncFetchNewsDB(type: String) {
        newsRepository.asyncFetchNewsDB(type)
    }

     fun asyncFetchNewsNet(pageIndex: Int, pageCount: Int, type: String) {
         viewModelScope.launch {
             newsRepository.asyncFetchNewsNet(pageIndex, pageCount, type)
         }
    }

    suspend fun asyncFetch(type: String) {
        viewModelScope.launch {
            asyncFetchNewsDB(type)

            if (newsRepository.data.value == null)
                asyncFetchNewsNet(1, 20, type)

            newsRepository.data.value?.apply {
                if (this.isEmpty())
                    asyncFetchNewsNet(1, 20, type)
            }

        }

    }


}