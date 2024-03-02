package db

import (
	"context"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"os"
	"time"
)

type MongoClient struct {
	Client     *mongo.Client
	Collection *mongo.Collection
}

func NewMongoClient() (*MongoClient, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	credentials := options.Credential{
		Username: os.Getenv("MONGO_USERNAME"),
		Password: os.Getenv("MONGO_PASSWORD"),
	}

	dbUrl := "mongodb://" + os.Getenv("MONGO_ADDR")

	client, err := mongo.Connect(ctx, options.Client().ApplyURI(dbUrl).SetAuth(credentials))
	if err != nil {
		return nil, err
	}

	collection := client.Database("url-shortener-service").Collection("urls")

	return &MongoClient{
		Client:     client,
		Collection: collection,
	}, nil
}
