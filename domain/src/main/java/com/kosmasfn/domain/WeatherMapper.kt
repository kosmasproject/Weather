package com.kosmasfn.domain

import com.kosmasfn.data.localdb.WeatherEntity
import com.kosmasfn.data.model.WeatherDataModel
import com.kosmasfn.domain.model.WeatherDomainModel

/**
 * Created by Kosmas on October 11, 2023.
 */
fun WeatherDataModel?.toDomainModel(): WeatherDomainModel {
    return WeatherDomainModel(
        this?.message ?: "",
        this?.cod ?: 0,
        this?.count ?: 0,
        this?.cities?.map { it.toDomainModel() } ?: listOf()
    )
}

fun WeatherDataModel.City?.toDomainModel(): WeatherDomainModel.City {
    return WeatherDomainModel.City(
        this?.base ?: "",
        this?.clouds.toDomainModel(),
        this?.cod ?: 0,
        this?.coord.toDomainModel(),
        this?.dt ?: 0,
        this?.id ?: 0,
        this?.main.toDomainModel(),
        this?.name ?: "",
        this?.sys.toDomainModel(),
        this?.timezone ?: 0,
        this?.visibility ?: 0,
        this?.weather?.map { it.toDomainModel() },
        this?.wind.toDomainModel()
    )
}

fun WeatherDataModel.Clouds?.toDomainModel(): WeatherDomainModel.Clouds {
    return WeatherDomainModel.Clouds(this?.all ?: 0)
}

fun WeatherDataModel.Coord?.toDomainModel(): WeatherDomainModel.Coord {
    return WeatherDomainModel.Coord(
        this?.lat ?: 0.0, this?.lon ?: 0.0
    )
}

fun WeatherDataModel.Main?.toDomainModel(): WeatherDomainModel.Main {
    return WeatherDomainModel.Main(
        this?.feelsLike ?: 0.0,
        this?.humidity ?: 0,
        this?.pressure ?: 0,
        this?.temp ?: 0.0,
        this?.tempMax ?: 0.0,
        this?.tempMin ?: 0.0
    )
}

fun WeatherDataModel.Sys?.toDomainModel(): WeatherDomainModel.Sys {
    return WeatherDomainModel.Sys(
        this?.country ?: "", this?.id ?: 0, this?.sunrise ?: 0, this?.sunset ?: 0, this?.type ?: 0
    )
}

fun WeatherDataModel.Weather?.toDomainModel(): WeatherDomainModel.Weather {
    return WeatherDomainModel.Weather(
        this?.description ?: "", this?.icon ?: "", this?.id ?: 0, this?.main ?: ""
    )
}

fun WeatherDataModel.Wind?.toDomainModel(): WeatherDomainModel.Wind {
    return WeatherDomainModel.Wind(
        this?.deg ?: 0, this?.speed ?: 0.0
    )
}

fun WeatherDomainModel.City?.toEntity(): WeatherEntity {
    return WeatherEntity(
        name = this?.name,
        lat = this?.coord?.lat,
        lon = this?.coord?.lon,
        humidity = this?.main?.humidity,
        pressure = this?.main?.pressure,
        temp = this?.main?.temp,
        tempMax = this?.main?.tempMax,
        tempMin = this?.main?.tempMin,
        country = this?.sys?.country,
        description = this?.weather?.get(0)?.description,
        icon = this?.weather?.get(0)?.icon
    )
}

fun WeatherEntity?.toDomainModel(): WeatherDomainModel.City {
    return WeatherDomainModel.City(
        name = this?.name,
        coord = WeatherDomainModel.Coord(this?.lat, this?.lon),
        main = WeatherDomainModel.Main(
            humidity = this?.humidity,
            pressure = this?.pressure,
            temp = this?.temp,
            tempMin = this?.tempMin,
            tempMax = this?.tempMax
        ),
        sys = WeatherDomainModel.Sys(country = this?.country),
        weather = listOf(WeatherDomainModel.Weather(description = this?.description, icon = this?.icon))
    )
}


