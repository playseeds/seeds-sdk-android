package com.playseeds.android.sdk.new_api.events;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Base class for the Seeds events that handles generating of the data. To use the Seeds events your
 * model classes should extend this one and put any value into it via
 * {@link BaseEvent#putAttribute(String, Number)} or {@link BaseEvent#putAttribute(String, String)}.
 */
public abstract class BaseEvent<T extends BaseEvent> {
    private final String eventName;

    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    /**
     * Constructor, that must be inherited.
     *
     * @param eventName the non-null name of the event.
     */
    public BaseEvent(String eventName) {

        if (eventName == null) throw new NullPointerException("Event name must not be null");

        this.eventName = eventName;
    }

    /**
     * Returns the name of the event
     *
     * @return the specified event name.
     */
    public String getType() {
        return eventName;
    }

    /**
     * Save the passed value with the key as additional attribute.
     *
     * @param key Name of the attribute.
     * @param value Attribute to log.
     * @return The {@link BaseEvent} implementation itself
     */
    public T putAttribute(@NonNull String key, @NonNull String value) {

        this.attributes.put(key, value);

        return (T) this;
    }

    /**
     * @see BaseEvent#putAttribute(String, String)
     */
    public T putAttribute(@NonNull String key, @NonNull Number value) {

        this.attributes.put(key, value);

        return (T) this;
    }

    protected Map<String, Object> getCustomAttributes() {
        return this.attributes;
    }

    @Override
    public String toString() {
        return "{Event:\"" + eventName + "\"" + ", attributes" + new Gson().toJson(attributes) + "}";
    }
}
