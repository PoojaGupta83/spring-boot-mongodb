/* **********************************************************************
 * 83incs CONFIDENTIAL
 **********************************************************************
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
package com.programmingexpense.springbootmongodb.mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class MongoConfig {
    @Value("${mongodb.database.username}")
    private String mongodbDatabaseUsernameAdmin;
    @Value("${mongodb.database.password}")
    private String mongodbDatabasePasswordAdmin;
    @Value("${mongodb.server}")
    private String mongodbServer;
    @Value("${mongodb.port:27017}")
    private String mongodbPort;
    @Value("${mongodb.database:flex83_devices}")
    private String mongodbDatabaseName;

    public String getMongodbDatabaseUsernameAdmin() {
        return mongodbDatabaseUsernameAdmin;
    }

    public void setMongodbDatabaseUsernameAdmin(String mongodbDatabaseUsernameAdmin) {
        this.mongodbDatabaseUsernameAdmin = mongodbDatabaseUsernameAdmin;
    }

    public String getMongodbDatabasePasswordAdmin() {
        return mongodbDatabasePasswordAdmin;
    }

    public void setMongodbDatabasePasswordAdmin(String mongodbDatabasePasswordAdmin) {
        this.mongodbDatabasePasswordAdmin = mongodbDatabasePasswordAdmin;
    }

    public String getMongodbServer() {
        return mongodbServer;
    }

    public void setMongodbServer(String mongodbServer) {
        this.mongodbServer = mongodbServer;
    }

    public String getMongodbPort() {
        return mongodbPort;
    }

    public void setMongodbPort(String mongodbPort) {
        this.mongodbPort = mongodbPort;
    }

    public String getMongodbDatabaseName() {
        return mongodbDatabaseName;
    }

    public void setMongodbDatabaseName(String mongodbDatabaseName) {
        this.mongodbDatabaseName = mongodbDatabaseName;
    }
}
