package com.kosmasfn.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kosmasfn.data.model.WeatherDataModel

/**
 * Created by Kosmas on October 11, 2023.
 */
@Entity
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "lat") val lat: Double? = null,
    @ColumnInfo(name = "lon") val lon: Double? = null,
    @ColumnInfo(name = "humidity") val humidity: Int? = null,
    @ColumnInfo(name = "pressure") val pressure: Int? = null,
    @ColumnInfo(name = "temp") val temp: Double? = null,
    @ColumnInfo(name = "temp_max") val tempMax: Double? = null,
    @ColumnInfo(name = "temp_min") val tempMin: Double? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "icon") val icon: String? = null

) : ModelEntity<WeatherDataModel.City> {

    override fun mapToEntity(): WeatherDataModel.City {
        return WeatherDataModel.City(
            name = name,
            coord = WeatherDataModel.Coord(lat, lon),
            main = WeatherDataModel.Main(
                humidity = humidity,
                pressure = pressure,
                temp = temp,
                tempMin = tempMin,
                tempMax = tempMax
            ),
            sys = WeatherDataModel.Sys(country = country),
            weather = listOf(WeatherDataModel.Weather(description = description, icon = icon))
        )
    }
}