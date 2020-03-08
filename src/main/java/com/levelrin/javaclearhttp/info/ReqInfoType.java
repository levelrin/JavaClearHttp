/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import java.util.List;
import java.util.Map;

/**
 * Request information.
 * Since we are using fluent interfaces, we need to pass around the request information.
 * This is responsible for keeping and providing the request information to HTTP objects.
 */
public interface ReqInfoType {

    /**
     * We need this to instantiate the Socket object.
     * @return HTTP host.
     */
    String host();

    /**
     * The port number corresponding to the protocol.
     * HTTP is 80, and HTTPS is 443.
     * We need this to instantiate the Socket object.
     * @return The port number.
     */
    int port();

    /**
     * We need this to define new headers.
     * @return HTTP headers.
     */
    Map<String, String> headers();

    /**
     * We will send this messages.
     * @return HTTP request messages.
     */
    List<String> messages();

}
