/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.request;

import java.util.List;
import java.util.Map;

/**
 * The record of HTTP request.
 */
public interface RequestRecordType {

    /**
     * The record of HTTP headers.
     * @return Map of headers.
     */
    Map<String, String> headers();

    /**
     * The record of HTTP body.
     * @return HTTP request body.
     */
    String body();

    /**
     * The messages sent to the server.
     * @return The sent messages.
     */
    List<String> messages();

}
