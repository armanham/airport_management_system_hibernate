package com.bdg.model.common;

import static com.bdg.validator.Validator.checkId;

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
        checkId(id);
        this.id = id;
    }
}