package com.svegon.capi.util.event;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ListenerList<L> extends ListenerCollection<L, List<L>> {
    ListenerList(List<L> listeners, Function<List<L>, L> invokerFactory) {
        super(listeners, invokerFactory);
    }

    @Override
    protected List<L> synchronize(List<L> listeners) {
        return Collections.synchronizedList(listeners);
    }
}
