package api

import (
	"context"
	"github.com/gofiber/fiber/v2"
	"go.mongodb.org/mongo-driver/bson"
	"time"
	"url-shortener-service/db"
	"url-shortener-service/generator"
)

type Routes struct {
	cache *db.RedisClient
	db    *db.MongoClient
}

func NewRoutes(cache *db.RedisClient, db *db.MongoClient) *Routes {
	return &Routes{
		cache: cache,
		db:    db,
	}
}

type UrlRecord struct {
	LongUrl  string `bson:"long_url"`
	ShortUrl string `bson:"short_url"`
}

func (r *Routes) ResolveUrlHandler(c *fiber.Ctx) error {
	url := c.Params("url")

	// check cache
	cachedUrl, err := r.cache.Client.Get(context.TODO(), url).Result()
	if err == nil {
		return c.Redirect(cachedUrl, fiber.StatusMovedPermanently)
	}

	// check db
	var result UrlRecord
	filter := bson.M{"short_url": url}

	err = r.db.Collection.FindOne(
		context.TODO(),
		filter,
	).Decode(&result)
	if err != nil {
		return c.Status(fiber.StatusNotFound).JSON(fiber.Map{"error": url + " not found"})
	}

	// set cache
	err = r.cache.Client.Set(context.TODO(), url, result.LongUrl, time.Minute*1).Err()
	if err != nil {
		return c.Status(fiber.StatusInternalServerError).JSON(fiber.Map{"error": err.Error()})
	}

	return c.Redirect(result.LongUrl, fiber.StatusMovedPermanently)
}

func (r *Routes) ShortenUrlHandler(c *fiber.Ctx) error {
	url := c.Query("url")
	if url == "" {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{"error": "url query param is required"})
	}

	generatedUniqueId := generator.GenerateId()

	newUrlRecord := UrlRecord{
		LongUrl:  url,
		ShortUrl: generatedUniqueId,
	}

	_, err := r.db.Collection.InsertOne(context.TODO(), newUrlRecord)
	if err != nil {
		return c.Status(fiber.StatusInternalServerError).JSON(fiber.Map{"error": err.Error()})
	}

	return c.Status(fiber.StatusCreated).JSON(fiber.Map{"short_url": generatedUniqueId})
}
