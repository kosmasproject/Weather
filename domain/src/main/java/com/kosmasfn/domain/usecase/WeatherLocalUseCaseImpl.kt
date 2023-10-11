package com.kosmasfn.domain.usecase

import com.kosmasfn.data.localdb.WeatherLocalRepository
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.domain.toEntity
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
class WeatherLocalUseCaseImpl @Inject constructor(
    private val repository: WeatherLocalRepository
) : WeatherLocalUseCase {

    override suspend fun saveNews(data: WeatherDomainModel.City): Boolean {
        repository.saveNews(data.toEntity())
        return true
    }

    override fun getNews(): List<WeatherDomainModel.City> =
        repository.getNews().map { it.toDomainModel() }

    override fun deleteNews(url: String): Boolean = repository.deleteNews(url)

    override fun findArticleOnLocal(url: String): Boolean {
        return repository.findArticleOnLocal(url).isNotEmpty()
    }
}