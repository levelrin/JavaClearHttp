/*
 * Copyright (c) 2020 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.record.RecordType;
import java.util.ArrayList;
import java.util.List;

/**
 * It's for testing.
 * We can inject fake replies.
 */
public final class FakeConnection implements ConnectionType {

    /**
     * Fake raw replies.
     */
    private final List<String> replies;

    /**
     * Secondary constructor.
     * It will pass the empty {@link List} to the primary constructor.
     */
    public FakeConnection() {
        this(new ArrayList<>());
    }

    /**
     * Primary constructor.
     * @param replies See {@link FakeConnection#replies}.
     */
    public FakeConnection(final List<String> replies) {
        this.replies = replies;
    }

    @Override
    public RecordType record(final ReqInfoType info) {
        return new Record(info, this.replies);
    }

}
