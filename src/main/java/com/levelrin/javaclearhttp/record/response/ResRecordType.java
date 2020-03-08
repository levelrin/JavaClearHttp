/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

import java.util.List;
import java.util.Map;

/**
 * The record of HTTP response.
 */
public interface ResRecordType {

    /**
     * Return HTTP status object.
     * @return Https status.
     */
    StatusType status();

    /**
     * Return HTTP response headers.
     * @return Map that contains HTTP response headers.
     */
    Map<String, String> headers();

    /**
     * Return HTTP response body.
     * @return HTTP response body.
     */
    String body();

    /**
     * Return raw replies from the server.
     * @return Raw replies from the server.
     */
    List<String> replies();

}
