package com.github.happyuky7.nameMCAPIServersMCCommon.api.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.UUID;

public class MongoDBManager {

    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public static MongoCollection uc;

    /*public MongoDBManager(MongoClient mongoClient, MongoDatabase mongoDatabase, MongoCollection mongoCollection) {
        this.mongoClient = mongoClient;
        this.database = mongoDatabase;
        this.uc = mongoCollection;
    }*/
    public MongoDBManager(String mongoURI, String databaseName, String collectionName) {
        this.mongoClient = MongoClients.create(mongoURI);
        this.database = mongoClient.getDatabase(databaseName);
        this.uc = database.getCollection(collectionName);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return database;
    }

    public MongoCollection getMongoCollection() {
        return uc;
    }


    public static void setVote(UUID uuid, Boolean vote) {
        Document user = (new Document("_id", uuid)).append("vote", vote);
        uc.insertOne(user);
    }

    public static Boolean getVote(UUID uuid) {
        Document user = (Document)uc.find((Bson)new Document("_id", uuid)).first();
        return user.getBoolean("vote");
    }

    public static void setReward(UUID uuid, Boolean reward) {
        Document user = (new Document("_id", uuid)).append("reward", reward);
        uc.insertOne(user);
    }

    public static Boolean getReward(UUID uuid) {
        Document user = (Document)uc.find((Bson)new Document("_id", uuid)).first();
        return user.getBoolean("reward");
    }

}
