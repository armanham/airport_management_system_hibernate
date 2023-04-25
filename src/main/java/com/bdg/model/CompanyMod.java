package com.bdg.model;

import java.sql.Date;
import java.util.Objects;

public class CompanyMod {

    private int id;
    private String name;
    private Date foundDate;


    public CompanyMod(final String name, final Date foundDate) {
        setName(name);
        setFoundDate(foundDate);
    }

    public CompanyMod() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be positive number: ");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'name': ");
        }
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(final Date foundDate) {
        if (foundDate == null) {
            throw new NullPointerException("Passed null value as 'foundDate': ");
        }
        this.foundDate = foundDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyMod that = (CompanyMod) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(foundDate, that.foundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, foundDate);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundDate=" + foundDate +
                "}\n";
    }
}