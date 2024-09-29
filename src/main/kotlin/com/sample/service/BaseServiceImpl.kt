package com.sample.service

import com.sample.model.BaseModel
import com.sample.protocol.BaseProtocol

class BaseServiceImpl(private val baseProtocol: BaseProtocol): BaseService {
    override suspend fun baseOperation(data: BaseModel): BaseModel {
        return baseProtocol.baseOperation(data)
    }
}