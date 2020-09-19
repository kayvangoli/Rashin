package com.k1apps.rashintestapp.di

import androidx.room.Room
import com.k1apps.rashintestapp.MyApp
import com.k1apps.rashintestapp.repository.ContentRepository
import com.k1apps.rashintestapp.infrastracture.content.ContentApi
import com.k1apps.rashintestapp.infrastracture.content.ContentBoundaryCallback
import com.k1apps.rashintestapp.infrastracture.content.ContentRepositoryImpl
import com.k1apps.rashintestapp.infrastracture.db.AppDatabase
import com.k1apps.rashintestapp.infrastracture.db.ContentDao
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kotlinx.coroutines.GlobalScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope


@Scope
@Retention
annotation class AppScope

@Suppress("unused")
@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBinder::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApp): AppComponent
    }
}


@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContentRepository(
        contentDao: ContentDao,
        boundaryCallback: ContentBoundaryCallback
    ): ContentRepository {
        return ContentRepositoryImpl(contentDao, boundaryCallback, GlobalScope)
    }

    @Provides
    @AppScope
    fun provideBoundaryCallback(
        contentApi: ContentApi,
        contentDao: ContentDao
    ): ContentBoundaryCallback {
        return ContentBoundaryCallback(GlobalScope, contentApi, contentDao)
    }

    @Provides
    @AppScope
    fun providesContentServiceApi(retrofit: Retrofit): ContentApi {
        return retrofit.create(ContentApi::class.java)
    }

    @Provides
    @AppScope
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://core.gapfilm.ir")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @AppScope
    fun provideLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Provides
    @AppScope
    fun provideContentDao(appDb: AppDatabase): ContentDao {
        return appDb.contentDao()
    }

    @Provides
    @AppScope
    fun provideAppDb(application: MyApp): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "movie-db"
        ).build()
    }


}