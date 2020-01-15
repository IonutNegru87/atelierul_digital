package com.inegru.android.atelieruldigital.helloworld.week9.db.dao;

import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Department;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

/**
 * .
 */
@SuppressWarnings("unused")
@Dao
public interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Department> departments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDepartment(Department department);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDepartment(Department department);

    @Delete
    void deleteDepartment(Department department);
}
