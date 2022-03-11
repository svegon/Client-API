package com.svegon.capi.util.event;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public abstract class ListenerCollection<L, C extends Collection<L>> {
    private final C listeners;
    private final L invoker;

    ListenerCollection(C listeners, Function<C, L> invokerFactory) {
        this.listeners = synchronize(listeners);
        this.invoker = invokerFactory.apply(this.listeners);
    }

    public L invoker() {
        return invoker;
    }

    public L register(L listener) {
        listeners.add(Preconditions.checkNotNull(listener));
        return listener;
    }

    public boolean unregister(L listener) {
        return listeners.remove(Preconditions.checkNotNull(listener));
    }

    protected abstract C synchronize(C listeners);
}
