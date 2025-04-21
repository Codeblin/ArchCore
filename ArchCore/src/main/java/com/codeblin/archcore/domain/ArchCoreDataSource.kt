package com.codeblin.archcore.domain

interface ArchCoreDataSource<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Int): T?
    suspend fun save(item: T): Boolean
    suspend fun delete(id: Int): Boolean
}