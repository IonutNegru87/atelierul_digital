package com.inegru.android.atelieruldigital.helloworld.week9.db;

import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Company;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Department;
import com.inegru.android.atelieruldigital.helloworld.week9.db.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;

/**
 * .
 */
class DataGenerator {

    private static final String[] COMPANIES = new String[]{
        "Google", "Apple", "LinkedIN"};

    private static final List<Company> companies = new ArrayList<>(COMPANIES.length);
    private static final List<Employee> employees = new ArrayList<>();
    private static final List<Department> departments = new ArrayList<>();

    static {
        Random rnd = new Random();
        for (int i = 0; i < COMPANIES.length; i++) {
            Company company = new Company();
            company.setName(COMPANIES[i]);
            company.setCompanyId(i + 1);
            companies.add(company);
        }

        for (Company company : companies) {
            int citiesNumber = rnd.nextInt(5) + 1;
            for (int i = 0; i < citiesNumber; i++) {
                Employee employee = new Employee();
                employee.setCompanyId(company.getCompanyId());
                employee.setName("Employee " + (i + 1) + " from " + company.getName());
                employees.add(employee);
            }

            int departmentNumbers = rnd.nextInt(5) + 1;
            for (int i = 0; i < departmentNumbers; i++) {
                Department department = new Department();
                department.setCompanyId(company.getCompanyId());
                department.setName("Dep " + i);
                departments.add(department);
            }
        }
    }

    private DataGenerator() {
    }

    @NonNull
    static List<Company> getCompanies() {
        return companies;
    }

    @NonNull
    static List<Department> getDepartments() {
        return departments;
    }

    @NonNull
    static List<Employee> getEmployees() {
        return employees;
    }
}
