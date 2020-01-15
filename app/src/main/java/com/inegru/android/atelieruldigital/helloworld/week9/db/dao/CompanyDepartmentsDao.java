package com.inegru.android.atelieruldigital.helloworld.week9.db.dao;

import com.inegru.android.atelieruldigital.helloworld.week9.model.CompanyAndAllDepartments;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * .
 */
@Dao
public interface CompanyDepartmentsDao {

    @Transaction
    @Query("SELECT * FROM Company WHERE id = :companyId")
    LiveData<CompanyAndAllDepartments> loadCompanyAndAllDepartments(long companyId);

}
