package com.dguitarclassic.todoapps

import android.content.Context
import androidx.room.Room
import com.dguitarclassic.todoapps.db.TodoDao
import com.dguitarclassic.todoapps.model.TodoDB
import com.dguitarclassic.todoapps.repo.TodoRepo
import com.dguitarclassic.todoapps.repo.TodoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDB {
        return Room.databaseBuilder(context, TodoDB::class.java, "todoDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideToDoDao(db: TodoDB): TodoDao {
        return db.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(
        todoDao: TodoDao
    ): TodoRepo {
        return TodoRepoImpl(todoDao)
    }
}