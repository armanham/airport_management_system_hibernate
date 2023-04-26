package com.bdg.converter.model_to_persistance;

import java.util.Collection;

public abstract class ModToPer<M, P> {

    public ModToPer() {
    }

    public abstract P getPersistentFrom(M model);

    public abstract Collection<P> getPersistentListFrom(Collection<M> modelList);
}