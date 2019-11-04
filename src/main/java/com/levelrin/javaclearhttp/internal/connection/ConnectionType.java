/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import java.util.List;

/**
 * It's responsible for making HTTP connection.
 * This class is created to remove duplicated code.
 */
public interface ConnectionType {

    /**
     * Make HTTP connection, send the messages, then return replies.
     * @param messages It will be sent to the server.
     * @return Replies from the server.
     */
    List<String> replies(List<String> messages);

}
