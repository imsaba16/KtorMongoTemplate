package com.sample.repository

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.sample.model.BaseModel
import com.sample.protocol.BaseProtocol

class BaseRepository(collection: MongoDatabase) : BaseProtocol {
    private val sampleCollection = collection.getCollection<BaseModel>("sample")
    override suspend fun baseOperation(data: BaseModel): BaseModel {
        val nextId = sampleCollection.countDocuments() + 1
        val new = BaseModel(data.name)
        sampleCollection.insertOne(new)
        return new
    }
}