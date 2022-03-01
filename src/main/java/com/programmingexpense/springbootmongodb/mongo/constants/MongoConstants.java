package com.programmingexpense.springbootmongodb.mongo.constants;

public interface MongoConstants {
    String MONGODB_SERVER_URL = "mongodb.server.url";
    String MONGODB_SERVER = "mongodb.server";
    String MONGODB_PORT = "mongodb.port";
    String MONGODB_DATABASE_NAME = "mongodb.database.name";
    String MONGODB_DATABASE_USERNAME = "mongodb.database.username";
    String MONGODB_DATABASE_PASSWORD = "mongodb.database.password";
    String MONGODB_AUTH_ENABLED = "mongodb.auth.enabled";
    String MONGODB_DB_USER_ADMIN = "mongodb.database.username.admin"; //admin username (with root role)
    String MONGODB_DB_PWD_ADMIN = "mongodb.database.password.admin";  //admin password (with root role)
    Integer MONGODB_QUERY_LOG_MAX_TIME = 200;
    String MONGO_CONFIG = "mongo.config";
    String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    String _READ = "_read";
    String _READ_WRITE = "_readWrite";
    String ADMIN_DB = "admin";

}
