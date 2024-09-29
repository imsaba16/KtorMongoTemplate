package com.sample.service

import com.sample.model.BaseModel

interface BaseService {
    suspend fun baseOperation(data: BaseModel): BaseModel
}