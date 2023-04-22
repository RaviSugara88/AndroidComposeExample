package com.sdd.saniproadvance.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sdd.saniproadvance.retrofit.network.ApiService
import com.sdd.saniproadvance.room_db.db.UserDao
import com.sdd.saniproadvance.room_db.db.UserDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /** start DI for Api*/
    @Provides
    @Singleton
    fun providesMoshi():Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi): ApiService =
        Retrofit
            .Builder()
            .run {
                baseUrl(ApiService.BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
                build()
            }.create(ApiService::class.java)


/** start DI for Room Data base */

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) : UserDatabase =
        Room.databaseBuilder(context,UserDatabase::class.java,"userData")
            .addMigrations(migration_1_2)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun providersUserDao(userDatabase: UserDatabase):UserDao =
        userDatabase.userDao()






    private val migration_1_2 =object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
          //  database.execSQL("ALTER TABLE work_plan_report ADD COLUMN isMobile BOOLEAN NOT NULL DEFAULT (FALSE)")
            //database.execSQL("ALTER TABLE work_plan_report ADD COLUMN commentBefore TEXT NOT NULL DEFAULT(test)")
            // database.execSQL("ALTER TABLE work_plan_report ADD COLUMN commentAtWork TEXT NOT NULL DEFAULT(test)")
            // database.execSQL("ALTER TABLE imageData ADD COLUMN commentAfter TEXT NOT NULL DEFAULT(test)")
            // database.execSQL("ALTER TABLE imageData ADD COLUMN commentBefore TEXT NOT NULL DEFAULT(test)")
            // database.execSQL("ALTER TABLE imageData ADD COLUMN commentAtWork TEXT NOT NULL DEFAULT(test)")
            //   database.execSQL(" TABLE imageData ADD COLUMN dateTime TEXT NOT NULL DEFAULT(01)")//Boolean
        }

    }

}