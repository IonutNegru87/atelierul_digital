package com.inegru.android.atelieruldigital.helloworld.week9.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * .
 */
@Entity(tableName = "Department")
public class Department {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "company_id")
    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Department{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", companyId=" + companyId +
            '}';
    }

}
