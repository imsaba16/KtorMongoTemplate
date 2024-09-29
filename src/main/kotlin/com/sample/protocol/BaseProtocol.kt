package com.sample.protocol

import com.sample.model.BaseModel

interface BaseProtocol {
    suspend fun baseOperation(data: BaseModel): BaseModel
}