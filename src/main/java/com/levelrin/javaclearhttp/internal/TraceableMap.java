/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Arrays;

/**
 * This library uses builder pattern to configure HTTP request.
 * That means configuration data needs to be passed from an object to object.
 * This map will be used to transfer those data.
 *
 * Moving data can be troublesome.
 * One of the common problems is that the data may not exist in the map.
 * In that case, an exception will be thrown.
 * And it would be helpful to check trace back the map usages.
 * Similar to logging, footprints can be recorded in this map.
 * When an exception is thrown, it will display those footprints.
 *
 * It's immutable.
 */
public final class TraceableMap {

    /**
     * A map to store HTTP request configuration.
     */
    private final Map<String, String> map;

    /**
     * The records of activities.
     * The footprints will be included in the exception message.
     */
    private final List<String> footprints;

    /**
     * Secondary constructor.
     */
    public TraceableMap() {
        this(new HashMap<>(), new ArrayList<>());
    }

    /**
     * Primary constructor.
     * It will be used internally for the sake of immutable structure.
     * @param map Info at {@link this#map}.
     * @param footprints Info at {@link this#footprints}.
     */
    private TraceableMap(final Map<String, String> map, final List<String> footprints) {
        this.map = new HashMap<>(map);
        this.footprints = new ArrayList<>(footprints);
    }

    /**
     * Clone itself including new pair.
     * @param key Key.
     * @param value Value.
     * @return Map that contains new pair.
     */
    public TraceableMap pair(final String key, final String value) {
        final Map<String, String> copy = new HashMap<>(this.map);
        this.checkNullArgs(key, value);
        copy.put(key, value);
        return new TraceableMap(copy, this.footprints);
    }

    /**
     * Clone itself including new footprint.
     * @param caller Use 'this' keyword.
     * @param method Method name that uses this.
     * @param message Description of activity.
     * @return Map contains new footprint.
     */
    public TraceableMap footprint(final Object caller, final String method, final String message) {
        final List<String> copy = new ArrayList<>(this.footprints);
        this.checkNullArgs(message);
        copy.add(caller.getClass().getName() + "::" + method + ": " + message);
        return new TraceableMap(this.map, copy);
    }

    /**
     * Retrieve value from the map.
     * @param key Key.
     * @return Value.
     */
    public String value(final String key) {
        this.checkNullArgs(key);
        this.checkExistence(key);
        return this.map.get(key);
    }

    /**
     * Check if the key exists in the map.
     * @param key Key.
     * @return True if the key exist.
     */
    public boolean contains(final String key) {
        this.checkNullArgs(key);
        return this.map.containsKey(key);
    }

    /**
     * All footprints as String.
     * It will be included in the exception message.
     * @return String form of footprints.
     */
    private String footprintsReport() {
        final StringJoiner report = new StringJoiner("\n");
        this.footprints.forEach(report::add);
        return report.toString();
    }

    /**
     * It checks if the key exists in the map.
     * It will do nothing if the key exists.
     * However, it will throw an exception if the key does not exist.
     * @param key Key.
     * @throws IllegalStateException It will be thrown if the key does not exist.
     */
    private void checkExistence(final String key) {
        if (!this.map.containsKey(key)) {
            throw new IllegalArgumentException(
                "The key doesn't exist.\n"
                + "Key: " + key + "\n"
                + "Map: " + this.map + "\n"
                + "Footprints:\n" + this.footprintsReport()
            );
        }
    }

    /**
     * Checks the arguments to make sure none of them are null.
     * @param args Target to be checked for nulls.
     * @throws IllegalArgumentException It will be thrown if any of arguments is null.
     */
    private void checkNullArgs(final String... args) {
        for (final String arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException(
                    "There is a null argument, which is not allowed.\n"
                    + "Args: " + Arrays.toString(args) + "\n"
                    + "Map: " + this.map + "\n"
                    + "Footprints: \n" + this.footprintsReport()
                );
            }
        }
    }

}
