package com.daniel.data.network.work

import androidx.lifecycle.Observer
import androidx.work.WorkInfo

interface IWorkState : Observer<List<WorkInfo>> {
    override fun onChanged(worksInfo: List<WorkInfo>)
}