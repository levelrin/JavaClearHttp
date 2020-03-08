/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * It's for testing.
 * You can inject {@link RecordType} via constructor.
 * It will simply return the injected object on its method call.
 */
public final class LeakedConnection implements ConnectionType {

    /**
     * We will simply return this.
     */
    private final RecordType fakeRecord;

    /**
     * Constructor.
     * @param fakeRecord See {@link LeakedConnection#fakeRecord}.
     */
    public LeakedConnection(final RecordType fakeRecord) {
        this.fakeRecord = fakeRecord;
    }

    @Override
    public RecordType record(final ReqInfoType info) {
        return this.fakeRecord;
    }

}
