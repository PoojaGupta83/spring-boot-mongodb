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
package com.programmingexpense.springbootmongodb.mongo.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.programmingexpense.springbootmongodb.handler.exception.ValidationException;
import com.programmingexpense.springbootmongodb.mongo.constants.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.programmingexpense.springbootmongodb.handler.response.ApiResponseCodeImpl.CONNECTION_FAILED;


@Service
public class ThirdPartyMongoRepository {
    private static final Logger LOG = LogManager.getLogger();


    public MongoClient getMongoClient(String host, int port) throws Exception {
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient(host, port);
        } catch (Exception e) {
            LOG.info("<=====Mongo Connection Error====> ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), CONNECTION_FAILED.getMessage());
        }
        return mongoClient;
    }


    public MongoClient getMongoClient(String host, int port, String database, String username, String password) throws Exception {
        MongoClient mongoClient;
        try {
            MongoCredential mongoCredential;
            mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
            ServerAddress serverAddress = new ServerAddress(host, port);
            mongoClient = new MongoClient(serverAddress, mongoCredential, MongoClientOptions.builder().build());
            LOG.info("Connection created Successfully with mongoDb ");

        } catch (Exception e) {
            LOG.info("<=====Mongo Connection Error====> ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), CONNECTION_FAILED.getMessage());
        }
        return mongoClient;

    }

    /**
     * This method is used to list all the databases after connection to the mongo Client.
     *
     * @return {List<String>} List of database names.
     * @throws Exception
     */

    public List<String> getMongoDatabases(MongoClient mongoClient) throws Exception {
        List<String> databaseList = new ArrayList<>();
        try {
            MongoCursor<String> mongoCursor = mongoClient.listDatabaseNames().iterator();
            while (mongoCursor.hasNext()) {
                databaseList.add(mongoCursor.next());
            }
        } catch (Exception e) {
            LOG.info("<=====Error while fetching databases List====> ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), CONNECTION_FAILED.getMessage());
        }
        return databaseList;
    }

    /**
     * This method is used to get information of a particular database present in mongoDb.
     *
     * @param database Name of the database
     * @throws Exception if no database found with the given name
     */

    public MongoDatabase getMongoDatabase(MongoClient mongoClient, String database) throws Exception {
        MongoDatabase mongoDatabase;
        try {
            mongoDatabase = mongoClient.getDatabase(database);
        } catch (Exception e) {
            LOG.info("<=====Error while fetching Mongo database====> ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "Error while fetching");
        }
        return mongoDatabase;
    }


    /**
     * This method is used to get List of collection names inside a particular database
     *
     * @param database Name of the database.
     * @return {List<String>} having name of collections in it.
     */

    public List<String> getDatabaseCollectionsList(MongoClient mongoClient, String database) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        return mongoDatabase.listCollectionNames().into(new ArrayList<String>());
    }


    /**
     * This method is used to find list of values from mongodb on the basis of collectionName with different
     *
     * @param collection is the name of the collection on which operation is being performed.
     * @param query      the query filter.
     * @param projection means selecting only the necessary data rather than selecting whole of the data of a document.
     * @param sort       is used to sort data either in ascending or descending order on the basis of some specific field.
     * @param offset     is specifically used for pagination it is an input integer argument
     *                   that specifies the number of documents to be skipped in a result set
     * @param max        is specifically used for pagination it is the size of documents to be displayed in a page.
     * @return it returns list of documents.
     */

    public List<Document> findList(MongoClient mongoClient, String database, String collection, Document query, Document projection, Document sort, Integer offset, Integer max) throws Exception {
        MongoDatabase mongoDatabase = getMongoDatabase(mongoClient, database);
        FindIterable<Document> iterable = mongoDatabase.getCollection(collection).find(query);
        if (Objects.nonNull(projection)) {
            iterable = iterable.projection(projection);
        }
        if (Objects.nonNull(sort)) {
            iterable = iterable.sort(sort);
        }
        if (Objects.nonNull(offset)) {
            iterable = iterable.skip(offset);
        }
        if (Objects.nonNull(max)) {
            iterable = iterable.limit(max);
        }
        List<Document> documents = iterable.into(new ArrayList<Document>());
        return documents;
    }

    public List<Document> aggregate(MongoClient mongoClient, String database, List<Document> pipeline, String collection) throws Exception {
        AggregateIterable<Document> iterable = getMongoDatabase(mongoClient, database).getCollection(collection).aggregate(pipeline).allowDiskUse(true);
        List<Document> documents = iterable.into(new ArrayList<>());
        return documents;
    }

    public List<Document> listIndexes(MongoClient mongoClient, String database, String collection) throws Exception {
        MongoCursor<Document> iterable = getMongoDatabase(mongoClient, database).getCollection(collection).listIndexes().iterator();
        List<Document> indexList = new ArrayList<>();
        try {
            while (iterable.hasNext()) {
                indexList.add(iterable.next());
            }
        } catch (Exception e) {
            LOG.info("<=====Error while fetching index List====> ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), "error while fetching index List");
        }
        return indexList;
    }

    /**
     * This method is used to count the number of documents in a collection of mongodb.
     *
     * @param collection is the name of the collection on which operation is being performed.
     * @param match      Filters the documents to pass only the documents that match the specified condition(s).
     * @return count of documents.
     */
    public long count(MongoClient mongoClient, String database, String collection, Document match) throws Exception {
        return Objects.nonNull(match) ? getMongoDatabase(mongoClient, database).getCollection(collection).count(match) : -1;
    }


    public long count(MongoClient mongoClient, String database, Document matchEq, Document searchParams, String collection) throws Exception {
        Document query = new Document();
        if (Objects.nonNull(matchEq)) {
            matchEq.forEach(query::put);
        }
        if (Objects.nonNull(searchParams)) {
            searchParams.forEach((k, v) -> {
                query.put(k, new Document("$regex", v).append("$options", "-i"));
            });
        }
        return this.count(mongoClient, database, collection, query);
    }

}
