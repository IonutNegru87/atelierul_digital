package com.inegru.android.atelieruldigital.helloworld.week9.model;

import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Department;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * .
 */
@SuppressWarnings("unused")
public class CompanyAndAllDepartments {

    @Embedded
    private Company company;

    @Relation(parentColumn = "id", entityColumn = "company_id", entity = Department.class)
    private List<Department> departments;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @NonNull
    @Override
    public String toString() {
        return "CompanyAndAllDepartments{" +
            "company=" + company.toString() +
            ", departments=" + departments.toString() +
            '}';
    }

}
