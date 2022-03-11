package com.svegon.capi.util.event;

import com.github.svegon.utils.collections.ListUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public final class ListenerCollectionFactory {
    private ListenerCollectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static  <L> ListenerList<L> listenersArrayList(Function<List<L>, L> invokerFactory) {
        return new ListenerList<>(Lists.newArrayList(), Preconditions.checkNotNull(invokerFactory));
    }

    public static  <L> ListenerList<L> listenersExposedArrayList(Function<List<L>, L> invokerFactory) {
        return new ListenerList<>(ListUtil.newExposedArrayList(), Preconditions.checkNotNull(invokerFactory));
    }

    public static  <L> ListenerSet<L> listenersHashSet(Function<Set<L>, L> invokerFactory) {
        return new ListenerSet<>(Sets.newHashSet(), Preconditions.checkNotNull(invokerFactory));
    }

    public static  <L> ListenerSet<L> listenersLinkedSet(Function<Set<L>, L> invokerFactory) {
        return new ListenerSet<>(Sets.newLinkedHashSet(), Preconditions.checkNotNull(invokerFactory));
    }
}
