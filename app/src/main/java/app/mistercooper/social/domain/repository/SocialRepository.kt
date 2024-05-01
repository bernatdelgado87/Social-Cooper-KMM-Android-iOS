package app.mistercooper.social.domain.repository

import app.mistercooper.social.domain.feature.home.model.FeedModel

interface SocialRepository {
    suspend fun getFeed(): FeedModel
}