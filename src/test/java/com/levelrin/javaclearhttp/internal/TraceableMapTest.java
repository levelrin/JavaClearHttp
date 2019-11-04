/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
@SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_INFERRED", justification = "Return value can be ignored because the method should throw exception.")
final class TraceableMapTest {

    @Test
    public void obtainValue() {
        final TraceableMap map = new TraceableMap()
            .pair("fruit", "apple")
            .pair("number", "1");
        MatcherAssert.assertThat(
            map.value("fruit"),
            CoreMatchers.equalTo("apple")
        );
    }

    @Test
    public void exceptionShouldIncludeFootprints() {
        String message = "";
        try {
            final TraceableMap map = new TraceableMap()
                .pair("fruit", "apple")
                .footprint(
                    this,
                    "exceptionShouldIncludeFootprints()",
                    "Insert fruit to the map."
                );
            map.value("test");
        } catch (final IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        MatcherAssert.assertThat(
            message,
            CoreMatchers.equalTo(
                "The key doesn't exist.\n"
                    + "Key: test\n"
                    + "Map: {fruit=apple}\n"
                    + "Footprints:\n"
                    + "com.levelrin.javaclearhttp.internal.TraceableMapTest"
                    + "::exceptionShouldIncludeFootprints(): Insert fruit to the map."
            )
        );
    }

    @Test
    public void nullNotAllowed() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new TraceableMap().pair(null, null)
        );
    }

    @Test
    public void shouldBeImmutable() {
        final TraceableMap origin = new TraceableMap();
        final TraceableMap reference = origin.pair("key", "value");
        MatcherAssert.assertThat(
            origin,
            CoreMatchers.not(
                CoreMatchers.equalTo(reference)
            )
        );
    }

}
