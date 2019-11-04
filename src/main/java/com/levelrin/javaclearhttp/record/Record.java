/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import com.levelrin.javaclearhttp.internal.Port;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import com.levelrin.javaclearhttp.record.request.RequestRecord;
import com.levelrin.javaclearhttp.record.request.RequestRecordType;
import com.levelrin.javaclearhttp.record.response.ResponseRecord;
import com.levelrin.javaclearhttp.record.response.ResponseRecordType;

import java.util.List;
import java.util.StringJoiner;

/**
 * It's responsible for providing the request and response records.
 * The {@link this#toString()} method can be useful for viewing all information at once.
 */
public final class Record implements RecordType {

    /**
     * A map that contains HTTP request configuration.
     * It should have host, path, protocol, and method.
     * Also, it may have body.
     */
    private final TraceableMap map;

    /**
     * A list that contains the messages sent to the server.
     */
    private final List<String> messages;

    /**
     * A list that contains the replies sent from the server.
     */
    private final List<String> replies;

    /**
     * Primary constructor.
     * @param map Info at {@link this#map}.
     * @param messages Info at {@link this#messages}.
     * @param replies Info at {@link this#replies}.
     */
    public Record(final TraceableMap map, final List<String> messages, final List<String> replies) {
        this.map = map;
        this.messages = messages;
        this.replies = replies;
    }

    @Override
    public RequestRecordType request() {
        return new RequestRecord(
            this.map.footprint(
                this, "request()", "Create RequestRecordType object."
            ),
            this.messages
        );
    }

    @Override
    public ResponseRecordType response() {
        return new ResponseRecord(this.replies);
    }

    @Override
    public String toString() {
        final StringJoiner description = new StringJoiner("\n")
            .add("Host: " + this.map.value("host"))
            .add("Path: " + this.map.value("path"))
            .add("Protocol: " + this.map.value("protocol"))
            .add("Port: " + new Port(this.map.value("protocol")))
            .add("Method: " + this.map.value("method"));
        if (this.map.contains("body")) {
            description.add("Body: " + this.map.value("body"));
        }
        description.add("\nMessages:")
            .add(new RequestRecord(this.map, this.messages).toString())
            .add("Replies:")
            .add(new ResponseRecord(this.replies).toString());
        return description.toString();
    }

}
