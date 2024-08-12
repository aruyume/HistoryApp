package com.example.historyapp.ui.fragment.history

import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.data.repositories.HistoryRepository
import com.example.historyapp.ui.fragment.base.viewmodel.BaseViewModel

class HistoryViewModel(private val repository: HistoryRepository) : BaseViewModel<HistoryModel>(repository) {

}
