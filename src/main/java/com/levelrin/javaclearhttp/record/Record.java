/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import com.levelrin.javaclearhttp.record.request.RequestRecord;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.request.ReqRecordType;
import com.levelrin.javaclearhttp.record.response.ResRecordType;
import com.levelrin.javaclearhttp.record.response.ResponseRecord;
import java.util.List;
import java.util.StringJoiner;

/**
 * It's responsible for providing the request and response records.
 * The {@link Record#toString()} method can be useful for viewing all information at once.
 */
public final class Record implements RecordType {

    /**
     * The request information.
     */
    private final ReqInfoType info;

    /**
     * Raw HTTP response messages.
     */
    private final List<String> replies;

    /**
     * Constructor.
     * @param info See {@link Record#info}.
     * @param replies See {@link Record#replies}.
     */
    public Record(final ReqInfoType info, final List<String> replies) {
        this.info = info;
        this.replies = replies;
    }

    @Override
    public ReqRecordType request() {
        return new RequestRecord(this.info);
    }

    @Override
    public ResRecordType response() {
        return new ResponseRecord(this.replies);
    }

    @Override
    public String toString() {
        final StringJoiner description = new StringJoiner("\n")
            .add(this.info.toString())
            .add("")
            .add("Replies:");
        this.replies.forEach(description::add);
        return description.toString();
    }

}
