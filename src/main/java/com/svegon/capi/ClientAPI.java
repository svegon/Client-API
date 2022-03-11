package com.svegon.capi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientAPI {
    private ClientAPI() {
        throw new UnsupportedOperationException();
    }

    public static Logger LOGGER = LogManager.getLogger("Client API");
}
