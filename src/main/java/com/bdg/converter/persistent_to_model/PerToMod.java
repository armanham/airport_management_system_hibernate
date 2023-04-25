package com.bdg.converter.persistent_to_model;

public abstract class PerToMod<P, M> {

    public PerToMod() {
    }

    public abstract M getModelFromPersistent(P persistent);
}
