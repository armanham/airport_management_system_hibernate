package com.bdg.converter.model_to_persistance;

public abstract class ModToPer<M, P> {

    public ModToPer() {
    }

    public abstract P getPersistentFromModel(M model);
}