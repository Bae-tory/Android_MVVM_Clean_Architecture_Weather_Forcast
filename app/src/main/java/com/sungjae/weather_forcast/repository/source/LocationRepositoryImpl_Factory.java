package com.sungjae.weather_forcast.repository.source;

import com.sungjae.weather_forcast.repository.remote.source.LocationRemoteDataSource;
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
public final class LocationRepositoryImpl_Factory implements Factory<LocationRepositoryImpl> {
  private final Provider<LocationRemoteDataSource> locationRemoteDataSourceProvider;

  public LocationRepositoryImpl_Factory(
      Provider<LocationRemoteDataSource> locationRemoteDataSourceProvider) {
    this.locationRemoteDataSourceProvider = locationRemoteDataSourceProvider;
  }

  @Override
  public LocationRepositoryImpl get() {
    return newInstance(locationRemoteDataSourceProvider.get());
  }

  public static LocationRepositoryImpl_Factory create(
      Provider<LocationRemoteDataSource> locationRemoteDataSourceProvider) {
    return new LocationRepositoryImpl_Factory(locationRemoteDataSourceProvider);
  }

  public static LocationRepositoryImpl newInstance(
      LocationRemoteDataSource locationRemoteDataSource) {
    return new LocationRepositoryImpl(locationRemoteDataSource);
  }
}
