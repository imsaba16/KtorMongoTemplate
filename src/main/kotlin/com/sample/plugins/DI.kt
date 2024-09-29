package com.sample.plugins

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.sample.protocol.BaseProtocol
import com.sample.repository.BaseRepository
import com.sample.service.BaseService
import com.sample.service.BaseServiceImpl
import org.koin.dsl.module

val appModule = module {
    single {
        val connectionString = ConnectionString("mongodb://localhost:27017")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        val mongoClient = MongoClient.create(settings)
        mongoClient.getDatabase("Sample")
    }
    single { BaseRepository(get()) }
    single<BaseProtocol> { BaseRepository(get()) }
    single<BaseService> { BaseServiceImpl(get()) }
}