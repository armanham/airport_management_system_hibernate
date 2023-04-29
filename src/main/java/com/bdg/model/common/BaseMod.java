package com.bdg.model.common;

import com.bdg.validator.Validator;

public abstract class BaseMod {

    private int id;

    public BaseMod(final int id) {
        setId(id);
    }

    public BaseMod() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }
}