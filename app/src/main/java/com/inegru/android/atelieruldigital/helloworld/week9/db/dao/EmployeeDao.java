package com.inegru.android.atelieruldigital.helloworld.week9.db.dao;

import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Employee;

import java.util.List;

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
public interface EmployeeDao {

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();

    @Insert
    void insertEmployee(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Employee> employees);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

}
