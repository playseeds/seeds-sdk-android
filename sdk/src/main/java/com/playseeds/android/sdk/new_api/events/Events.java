package com.playseeds.android.sdk.new_api.events;

/**
 * An interface, that is used in the {@link com.playseeds.android.sdk.Seeds} to wrap the block of the
 * functionality, that is provided by the Seeds SDK.
 */
public interface Events {

    /**
     * Logs the passed event to the server.
     *
     * @param event is the event to log.
     */
    void logEvent(BaseEvent event);

    /**
     * Logs the user data to the server.
     *
     * @param info is the user data to log.
     */
    void logUser(UserInfo info);
}
