package com.inegru.android.atelieruldigital.helloworld.week9;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;
import com.inegru.android.atelieruldigital.helloworld.week9.db.AppDatabase;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDepartmentsDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Department;
import com.inegru.android.atelieruldigital.helloworld.week9.model.CompanyAndAllDepartments;

import java.util.List;
import java.util.Locale;

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
    private TextView tvCompanies;
    private TextView tvCompanyDepartments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_storage);

        AppDatabase db = ((AtelierulDigitalApp) getApplicationContext()).getDatabase();
        companyDao = db.companyDao();
        companyDepartmentsDao = db.companyDepartmentsDao();

        tvCompanies = findViewById(R.id.tv_companies);
        tvCompanyDepartments = findViewById(R.id.tv_company_departments);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppDatabase db = ((AtelierulDigitalApp) getApplicationContext()).getDatabase();
        db.isDatabaseCreated().observe(this, isDbCreated -> {
            // React to DB creation event

        });

        LiveData<List<Company>> allCompaniesLiveData = companyDao.getAllCompaniesOrdered();
        allCompaniesLiveData.observe(this, companies -> {
            Log.d(TAG, "All companies:\n" + companies);
            tvCompanies.setText("Companies:");
            for (Company company : companies) {
                tvCompanies.append("\n" + company);
            }
        });

        final long companyId = 2;

        LiveData<CompanyAndAllDepartments>
            companyDepartmentsLiveData = companyDepartmentsDao.loadCompanyAndAllDepartments(
            companyId);
        companyDepartmentsLiveData.observe(this, companyAndAllDepartments -> {
            Log.d(TAG,
                  "Found Departments for Company with ID = " + companyId + ":\n"
                      + companyAndAllDepartments);
            if (companyAndAllDepartments != null) {
                tvCompanyDepartments.setText(String.format("Departments for company %s:",
                                                           companyAndAllDepartments.getCompany()
                                                                                   .getName()));
                for (Department department : companyAndAllDepartments.getDepartments()) {
                    tvCompanyDepartments.append("\n" + department);
                }
            } else {
                tvCompanyDepartments.setText(
                    String.format(Locale.getDefault(), "Invalid company with ID=%d", companyId));
            }
        });
    }
}
