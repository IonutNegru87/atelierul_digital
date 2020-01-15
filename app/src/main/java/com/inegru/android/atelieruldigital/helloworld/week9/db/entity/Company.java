package com.inegru.android.atelieruldigital.helloworld.week9.db.entity;

import android.graphics.Bitmap;

import com.inegru.android.atelieruldigital.helloworld.week9.db.converter.DateConverter;
import com.inegru.android.atelieruldigital.helloworld.week9.model.Location;

import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * .
 */
@Entity(tableName = "Company")
public class Company {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int companyId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date_updated")
    @TypeConverters(DateConverter.class)
    private Date itemUpdatedDate;

    @Embedded
    private Location location;

    @Embedded(prefix = "hq_")
    private Location headLocation;

    @Ignore
    private Bitmap picture;

    public Company() {
    }

    @Ignore
    public Company(String name, Date itemUpdatedDate, Location location, Location headLocation) {
        this.name = name;
        this.itemUpdatedDate = itemUpdatedDate;
        this.location = location;
        this.headLocation = headLocation;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getItemUpdatedDate() {
        return itemUpdatedDate;
    }

    public void setItemUpdatedDate(Date itemUpdatedDate) {
        this.itemUpdatedDate = itemUpdatedDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getHeadLocation() {
        return headLocation;
    }

    public void setHeadLocation(Location headLocation) {
        this.headLocation = headLocation;
    }

    @SuppressWarnings("unused")
    public Bitmap getPicture() {
        return picture;
    }

    @SuppressWarnings("unused")
    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, itemUpdatedDate, location, headLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
            Objects.equals(itemUpdatedDate, company.itemUpdatedDate) &&
            Objects.equals(location, company.location) &&
            Objects.equals(headLocation, company.headLocation);
    }

    @NonNull
    @Override
    public String toString() {
        return "Company{" +
            "companyId=" + companyId +
            ", name='" + name + '\'' +
            ", itemUpdatedDate=" + itemUpdatedDate +
            ", location=" + location +
            ", headLocation=" + headLocation +
            ", picture=" + picture +
            '}';
    }
}
