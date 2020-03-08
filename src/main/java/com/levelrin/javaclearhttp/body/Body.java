/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.body;

import com.levelrin.javaclearhttp.connection.ConnectionType;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP body.
 * It's responsible for sending a HTTP request.
 */
public final class Body implements BodyType {

    /**
     * Request information.
     */
    private final ReqInfoType info;

    /**
     * An object that is responsible for making HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Constructor.
     * @param info See {@link Body#info}.
     * @param connection See {@link Body#connection}.
     */
    public Body(final ReqInfoType info, final ConnectionType connection) {
        this.info = info;
        this.connection = connection;
    }

    @Override
    public RecordType send() {
        return this.connection.record(this.info);
    }

}
