/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.codecrow.mage.data.channels.impl

import io.codecrow.mage.data.Result
import io.codecrow.mage.data.channels.ChannelRepository
import io.codecrow.mage.model.Channel
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of InterestRepository that returns a hardcoded list of
 * topics, people and publications synchronously.
 */
class ChannelRepositoryImpl : ChannelRepository {
    override val channels: Flow<List<Channel>>
        get() = TODO("Not yet implemented")

    override suspend fun getChannels(): Result<List<Channel>> {
        TODO("Not yet implemented")
    }
}