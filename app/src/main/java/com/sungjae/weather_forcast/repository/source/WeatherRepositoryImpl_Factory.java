package com.sungjae.weather_forcast.repository.source;

import com.sungjae.weather_forcast.repository.remote.source.WeatherRemoteDataSource;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WeatherRepositoryImpl_Factory implements Factory<WeatherRepositoryImpl> {
  private final Provider<WeatherRemoteDataSource> weatherRemoteDataSourceProvider;

  public WeatherRepositoryImpl_Factory(
      Provider<WeatherRemoteDataSource> weatherRemoteDataSourceProvider) {
    this.weatherRemoteDataSourceProvider = weatherRemoteDataSourceProvider;
  }

  @Override
  public WeatherRepositoryImpl get() {
    return newInstance(weatherRemoteDataSourceProvider.get());
  }

  public static WeatherRepositoryImpl_Factory create(
      Provider<WeatherRemoteDataSource> weatherRemoteDataSourceProvider) {
    return new WeatherRepositoryImpl_Factory(weatherRemoteDataSourceProvider);
  }

  public static WeatherRepositoryImpl newInstance(WeatherRemoteDataSource weatherRemoteDataSource) {
    return new WeatherRepositoryImpl(weatherRemoteDataSource);
  }
}
