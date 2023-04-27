package com.bdg.model;

import com.bdg.validator.Validator;

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
        Validator.checkId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(final Date foundDate) {
        Validator.checkNull(foundDate);
        this.foundDate = foundDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyMod that = (CompanyMod) o;
        return Objects.equals(name, that.name) && Objects.equals(foundDate, that.foundDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foundDate);
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