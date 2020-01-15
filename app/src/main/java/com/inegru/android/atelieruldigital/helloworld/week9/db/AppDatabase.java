package com.inegru.android.atelieruldigital.helloworld.week9.db;

import android.content.Context;
import android.util.Log;

import com.inegru.android.atelieruldigital.helloworld.week9.AppExecutors;
import com.inegru.android.atelieruldigital.helloworld.week9.db.converter.DateConverter;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.CompanyDepartmentsDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.DepartmentDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.dao.EmployeeDao;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Department;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * .
 */
@SuppressWarnings({"DefaultAnnotationParam", "WeakerAccess"})
@Database(
    entities = {Company.class, Department.class, Employee.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DateConverter.class
)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";

    @VisibleForTesting
    public static final String DB_NAME = "atelierul_digital.db";

    private static volatile AppDatabase instance;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = create(context, executors);
                }
            }
        }
        return instance;
    }

    @NonNull
    private static AppDatabase create(@NonNull final Context context,
                                      @NonNull final AppExecutors executors) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                                              AppDatabase.class,
                                              DB_NAME)
                             .addCallback(new Callback() {
                                 @Override
                                 public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                     super.onCreate(db);
                                     executors.diskIO().execute(() -> {
                                         // Add a delay to simulate a long-running operation
                                         addDelay();
                                         // Generate the data for pre-population
                                         AppDatabase database = AppDatabase
                                             .getInstance(context.getApplicationContext(),
                                                          executors);

                                         // Pre-populate DB with dummy data
                                         insertData(database, DataGenerator.getCompanies(),
                                                    DataGenerator.getEmployees(),
                                                    DataGenerator.getDepartments());
                                         // notify that the database was created and it's ready
                                         // to be used
                                         database.setDatabaseCreated();
                                     });
                                 }
                             })
                             // Create migration strategy if needed
                             // .addMigrations(MIGRATION_1_2)
                             .build();
        db.updateDatabaseCreated(context.getApplicationContext());
        return db;
    }

    private static void insertData(final AppDatabase db,
                                   final List<Company> companies,
                                   final List<Employee> employees,
                                   final List<Department> departments) {
        db.runInTransaction(() -> {
            Log.d(TAG, "insertData: started transaction for inserting dummy data in DB!");
            db.companyDao().insertAll(companies);
            db.employeeDao().insertAll(employees);
            db.departmentDao().insertAll(departments);
        });
    }

    private static void addDelay() {
        try {
            // Sleep for 5 seconds
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
    }

    public abstract CompanyDao companyDao();

    public abstract EmployeeDao employeeDao();

    public abstract DepartmentDao departmentDao();

    public abstract CompanyDepartmentsDao companyDepartmentsDao();


    private void updateDatabaseCreated(@NonNull final Context context) {
        if (context.getDatabasePath(DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> isDatabaseCreated() {
        // Useful to check when we need to fetch data from server
        return isDatabaseCreated;
    }
}
