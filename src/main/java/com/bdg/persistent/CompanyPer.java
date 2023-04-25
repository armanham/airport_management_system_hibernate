package com.bdg.persistent;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "company")
public class CompanyPer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 24)
    private String name;

    @Column(name = "found_date", nullable = false, updatable = false)
    private Date foundDate;

    public CompanyPer() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(final Date foundDate) {
        this.foundDate = foundDate;
    }
}