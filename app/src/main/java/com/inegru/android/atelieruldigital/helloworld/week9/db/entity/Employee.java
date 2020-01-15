package com.inegru.android.atelieruldigital.helloworld.week9.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * .
 */
@Entity(
    tableName = "Employee",
    foreignKeys = @ForeignKey(entity = Company.class,
        parentColumns = "id",
        childColumns = "company_id",
        onDelete = ForeignKey.CASCADE)

)
public class Employee {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int employeeId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "company_id")
    private int companyId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Employee{" +
            "employeeId=" + employeeId +
            ", name='" + name + '\'' +
            ", companyId=" + companyId +
            '}';
    }
}
