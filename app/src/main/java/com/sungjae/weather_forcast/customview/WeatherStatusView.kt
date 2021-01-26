package com.sungjae.weather_forcast.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.sungjae.weather_forcast.R
import com.sungjae.weather_forcast.databinding.ViewWeatherStatusBinding
import com.sungjae.weather_forcast.presentation.bindingadapter.loadUrl
import com.sungjae.weather_forcast.presentation.component.enum.Weather

@BindingMethods(
    value = [
        BindingMethod(
            type = WeatherStatusView::class,
            method = "setWeatherImageAbbr",
            attribute = "app:weatherImageAbbr"
        ),
        BindingMethod(
            type = WeatherStatusView::class,
            method = "setWeatherName",
            attribute = "app:weatherName"
        ),
        BindingMethod(
            type = WeatherStatusView::class,
            method = "setCurrentTemp",
            attribute = "app:currentTemp"
        ),
        BindingMethod(
            type = WeatherStatusView::class,
            method = "setHumidity",
            attribute = "app:humidity"
        )]
)
class WeatherStatusView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private val binding: ViewWeatherStatusBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_weather_status,
            this,
            true
        )


    fun setWeatherImageAbbr(url: String) {
        sthWithWheatherEnums(url)
        binding.ivWeather.loadUrl(
            context.getString(R.string.url_weather_png_64, BASE_IMAGE_PARSING_URL, url),
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)
                ?: throw NullPointerException("placeholder res is null")
        )
    }


    /**
    * @see Weather enum class
     * this for abbr response
    * */
    private fun sthWithWheatherEnums(url: String) {
        when (url) {
            Weather.Snow.abbr -> {
            }
            Weather.Sleet.abbr -> {
            }
            Weather.Hail.abbr -> {
            }
            Weather.Thunderstorm.abbr -> {
            }
            Weather.HeavyRain.abbr -> {
            }
            Weather.LightRain.abbr -> {
            }
            Weather.Showers.abbr -> {
            }
            Weather.HeavyCloud.abbr -> {
            }
            Weather.LightCloud.abbr -> {
            }
            Weather.Clear.abbr -> {
            }
        }
    }

    fun setWeatherName(statusName: String) {
        binding.tvWeatherStatus.text = statusName
    }

    fun setCurrentTemp(temp: Float) {
        binding.tvWeatherCurrentTemp.text = context.getString(R.string.celsius, temp.toInt())

    }

    fun setHumidity(humidity: Int) {
        binding.tvWeatherHumidity.text = context.getString(R.string.percent, humidity)
    }

    private inline fun bind(action: ViewWeatherStatusBinding.() -> Unit) {
        binding.run(action)
    }

    companion object {

        private const val BASE_IMAGE_PARSING_URL = "https://www.metaweather.com/static/img/weather"

    }
}