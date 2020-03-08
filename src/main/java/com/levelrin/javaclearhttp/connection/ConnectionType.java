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
 * It's responsible for making HTTP connection.
 * This class is created to remove duplicated code.
 */
public interface ConnectionType {

    /**
     * Make HTTP connection, send the messages, then return replies.
     * @param info It will provide the request messages.
     * @return Replies from the server.
     */
    RecordType record(ReqInfoType info);

}
