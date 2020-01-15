package com.inegru.android.atelieruldigital.helloworld.week9;

import android.os.Bundle;
import android.util.Log;

import com.inegru.android.atelieruldigital.helloworld.week9.db.AppDatabase;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDepartmentsDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;
import com.inegru.android.atelieruldigital.helloworld.week9.model.CompanyAndAllDepartments;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

/**
 * .
 */
public class StorageActivity extends AppCompatActivity {

    private static final String TAG = "StorageActivity";

    private CompanyDao companyDao;
    private CompanyDepartmentsDao companyDepartmentsDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = ((AtelierulDigitalApp) getApplicationContext()).getDatabase();
        companyDao = db.companyDao();
        companyDepartmentsDao = db.companyDepartmentsDao();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppDatabase db = ((AtelierulDigitalApp) getApplicationContext()).getDatabase();
        db.isDatabaseCreated().observe(this, isDbCreated -> {
            // React to DB creation event

        });

        LiveData<List<Company>> allCompaniesLiveData = companyDao.getAllCompaniesOrdered();
        allCompaniesLiveData.observe(this, companies ->
            Log.d(TAG, "Found companies in DB:\n" + companies));

        LiveData<CompanyAndAllDepartments>
            companyDepartmentsLiveData = companyDepartmentsDao.loadCompanyAndAllDepartments(2);
        companyDepartmentsLiveData.observe(this, companyAndAllDepartments ->
            Log.d(TAG,
                  "Found Departments for Company with ID = 2:\n" + companyAndAllDepartments));
    }
}
