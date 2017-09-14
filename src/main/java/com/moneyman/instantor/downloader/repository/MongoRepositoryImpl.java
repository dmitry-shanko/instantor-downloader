package com.moneyman.instantor.downloader.repository;

public final class MongoRepositoryImpl  implements MongoRepository {

    private static final String HOST = "host";
    private static final String PORT = "port";

    //private MongoClient mongoClient;

    private MongoRepositoryImpl() {
        //mongoClient = new MongoClient(HOST, PORT);
    }

    private void init() {

    }

    public static final MongoRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final MongoRepository INSTANCE = new MongoRepositoryImpl();
    }
}
