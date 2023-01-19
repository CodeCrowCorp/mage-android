/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.codecrow.mage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import io.codecrow.mage.data.local.database.Browse
import io.codecrow.mage.data.local.database.BrowseDao
import javax.inject.Inject

interface BrowseRepository {
    val browses: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultBrowseRepository @Inject constructor(
    private val browseDao: BrowseDao
) : BrowseRepository {

    override val browses: Flow<List<String>> =
        browseDao.getBrowses().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        browseDao.insertBrowse(Browse(name = name))
    }
}
