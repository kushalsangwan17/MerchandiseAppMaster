package com.example.merchandiseapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {

    private productDao mproductDao;


    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mproductDao = db.prodDao();

    }



    LiveData<List<product>> gettheproducts(String interestedtype) {
         LiveData<List<product>> mAllproducts;
       mAllproducts= mproductDao.gettheproducts(interestedtype);
        return mAllproducts;
    }
    LiveData<List<product>> getsoldproducts(String merchantName) {
        LiveData<List<product>> msoldproducts;
        msoldproducts= mproductDao.getsoldproducts(merchantName);
        return msoldproducts;
    }


    public void insert (product prod) {
        new insertAsyncTask(mproductDao).execute(prod);
    }

    private static class insertAsyncTask extends AsyncTask<product, Void, Void> {

        private productDao mAsyncTaskDao;

        insertAsyncTask(productDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final product... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete (product prod) {
        new deleteAsyncTask(mproductDao).execute(prod);
    }

    private static class deleteAsyncTask extends AsyncTask<product, Void, Void> {

        private productDao mAsyncTaskDao;

        deleteAsyncTask(productDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final product... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void update (product prod) {
        new updateAsyncTask(mproductDao).execute(prod);
    }

    private static class updateAsyncTask extends AsyncTask<product, Void, Void> {

        private productDao mAsyncTaskDao;

        updateAsyncTask(productDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final product... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void deleteall() {
        new deleteallAsyncTask(mproductDao).execute();
    }

    private static class deleteallAsyncTask extends AsyncTask<Void, Void, Void> {

        private productDao mAsyncTaskDao;

        deleteallAsyncTask(productDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mAsyncTaskDao.deleteall();
            return null;
        }
    }
}
