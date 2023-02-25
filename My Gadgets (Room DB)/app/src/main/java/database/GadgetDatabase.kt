package database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentlist.Gadget

@Database(entities = [Gadget::class], version = 1)
abstract class GadgetDatabase: RoomDatabase() {

    abstract fun gadgetDao(): GadgetDAO

}