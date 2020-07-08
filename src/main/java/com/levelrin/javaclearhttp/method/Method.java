/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.method;

import com.levelrin.javaclearhttp.body.Body;
import com.levelrin.javaclearhttp.body.BodyType;
import com.levelrin.javaclearhttp.connection.ConnectionType;
import com.levelrin.javaclearhttp.header.Header;
import com.levelrin.javaclearhttp.header.HeaderType;
import com.levelrin.javaclearhttp.info.BodyInfo;
import com.levelrin.javaclearhttp.info.HeaderInfo;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP method.
 * HTTP header can be defined via this.
 * Or, HTTP request can be sent without defining headers.
 */
public final class Method implements MethodType {

    /**
     * Request information.
     */
    private final ReqInfoType info;

    /**
     * An object that is responsible for making HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Primary constructor.
     * @param info See {@link Method#info}.
     * @param connection See {@link Method#connection}.
     */
    public Method(final ReqInfoType info, final ConnectionType connection) {
        this.info = info;
        this.connection = connection;
    }

    @Override
    public HeaderType header(final String name, final String value) {
        return new Header(
            new HeaderInfo(
                this.info,
                name,
                value
            ),
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
