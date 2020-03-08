/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.LeakedPortInfo;
import com.levelrin.javaclearhttp.record.IgnoredRecord;
import com.levelrin.javaclearhttp.record.RecordType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class SocketConnectionTest {

    @Test
    public void port80ShouldReturnRecordFromFirstConnection() {
        final RecordType httpRecord = new IgnoredRecord();
        final RecordType httpsRecord = new IgnoredRecord();
        final int httpPort = 80;
        MatcherAssert.assertThat(
            new SocketConnection(
                new LeakedConnection(httpRecord),
                new LeakedConnection(httpsRecord)
            ).record(
                new LeakedPortInfo(httpPort)
            ),
            CoreMatchers.sameInstance(httpRecord)
        );
    }

    @Test
    public void port443ShouldReturnRecordFromSecondConnection() {
        final RecordType httpRecord = new IgnoredRecord();
        final RecordType httpsRecord = new IgnoredRecord();
        final int httpsPort = 443;
        MatcherAssert.assertThat(
            new SocketConnection(
                new LeakedConnection(httpRecord),
                new LeakedConnection(httpsRecord)
            ).record(
                new LeakedPortInfo(httpsPort)
            ),
            CoreMatchers.sameInstance(httpsRecord)
        );
    }

    @Test
    public void shouldThrowExceptionOnUnknownPort() {
        final int unknownPort = 123;
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new SocketConnection(
                new IgnoredConnection(),
                new IgnoredConnection()
            ).record(
                new LeakedPortInfo(unknownPort)
            )
        );
    }

}
