/* ***********************************************************************
 * 83incs CONFIDENTIAL
 * ***********************************************************************
 *
 *  [2017] - [2022] 83incs Ltd.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of 83incs Ltd, IoT83 Ltd, its suppliers (if any), its subsidiaries (if any) and
 * Source Code Licensees (if any).  The intellectual and technical concepts contained
 * herein are proprietary to 83incs Ltd, IoT83 Ltd, its subsidiaries (if any) and
 * Source Code Licensees (if any) and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from 83incs Ltd or IoT83 Ltd.
 ****************************************************************************
 */

package com.programmingexpense.springbootmongodb.mongo.dto.result;

public class UpdateDocResult {

    private Long updateCount;

    private Long matchedCount;

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public Long getMatchedCount() {
        return matchedCount;
    }

    public void setMatchedCount(Long matchedCount) {
        this.matchedCount = matchedCount;
    }
}
