package com.example.merchandiseapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {product.class}, version = 1, exportSchema = false)
public abstract class ProductRoomDatabase extends RoomDatabase {

    public abstract productDao prodDao();
    private static ProductRoomDatabase INSTANCE;

    static ProductRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "product_database")
                            // Wipes all the existing data added temporarily

                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final productDao mDao;
       product p1=new product("men","shirt","red","43","54","Rs. 2345","true","gogi","Denim jeans","Comfortable and Stylish");
        product p2=new product("women","lehenga","pink","56","62","Rs. 4567","false","gogi","Manyavar Lehnenga","Sparkling ang Shiny");

        product[] products={p1,p2};

        PopulateDbAsync(ProductRoomDatabase db) {
            mDao = db.prodDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.

            // when it is first created random objects are initially insertes
            mDao.deleteall();
            for (int i = 0; i <= products.length - 1; i++) {

                mDao.insert(products[i]);
            }
            return null;
        }
    }

}
