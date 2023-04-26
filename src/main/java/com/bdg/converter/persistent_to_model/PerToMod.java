package com.bdg.converter.persistent_to_model;

import java.util.Collection;

public abstract class PerToMod<P, M> {

    public PerToMod() {
    }

    public abstract M getModelFrom(P persistent);

    public abstract Collection<M> getModelListFrom(Collection<P> persistentList);
}
