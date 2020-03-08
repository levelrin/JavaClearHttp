/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.header;

import com.levelrin.javaclearhttp.body.Body;
import com.levelrin.javaclearhttp.connection.ConnectionType;
import com.levelrin.javaclearhttp.info.BodyInfo;
import com.levelrin.javaclearhttp.info.HeaderInfo;
import com.levelrin.javaclearhttp.body.BodyType;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP header.
 * Additional header can be defined via builder pattern.
 * HTTP body can be defined via this.
 * Or, HTTP request can be sent without defining body.
 */
public final class Header implements HeaderType {

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
     * @param info See {@link Header#info}.
     * @param connection See {@link Header#connection}.
     */
    public Header(final ReqInfoType info, final ConnectionType connection) {
        this.info = info;
        this.connection = connection;
    }

    @Override
    public HeaderType header(final String name, final String value) {
        return new Header(
            new HeaderInfo(this.info, name, value),
            this.connection
        );
    }

    @Override
    public BodyType body(final String content) {
        return new Body(
            new BodyInfo(
                new HeaderInfo(
                    this.info,
                    "Content-Length",
                    Integer.toString(content.length())
                ),
                content
            ),
            this.connection
        );
    }

    @Override
    public RecordType send() {
        return this.connection.record(this.info);
    }

}
