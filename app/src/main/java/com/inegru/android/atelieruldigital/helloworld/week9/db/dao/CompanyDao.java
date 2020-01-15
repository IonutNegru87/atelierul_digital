package com.inegru.android.atelieruldigital.helloworld.week9.db.dao;

import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * .
 */
@SuppressWarnings("unused")
@Dao
public interface CompanyDao {

    @Query("SELECT * FROM Company")
    List<Company> getAllCompanies();

    @Query("SELECT * FROM Company ORDER BY name ASC")
    LiveData<List<Company>> getAllCompaniesOrdered();

    @Query("SELECT * FROM Company WHERE name = :companyName ORDER BY name ASC")
    List<Company> getCompanies(String companyName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Company> companies);

    @Insert
    void insertCompany(Company company);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCompany(Company company);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCompanies(Company... companies);

    @Delete
    void deleteCompany(Company company);

    @Delete
    void deleteCompanies(Company... companies);
}
