package com.svegon.capi.util.event;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class ListenerSet<L> extends ListenerCollection<L, Set<L>> {
    ListenerSet(Set<L> listeners, Function<Set<L>, L> invokerFactory) {
        super(listeners, invokerFactory);
    }

    @Override
    protected Set<L> synchronize(Set<L> listeners) {
        return Collections.synchronizedSet(listeners);
    }
}
