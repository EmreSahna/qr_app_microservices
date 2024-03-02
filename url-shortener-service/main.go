package main

import (
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/logger"
	"github.com/joho/godotenv"
	"log"
	"url-shortener-service/api"
	"url-shortener-service/db"
)

func main() {
	// load .env file
	if err := godotenv.Load(); err != nil {
		log.Fatal("Error loading .env file")
	}

	// create redis client
	redisClient, err := db.NewRedisClient()
	if err != nil {
		log.Fatal(err)
	}

	// create mongo client
	mongoClient, err := db.NewMongoClient()
	if err != nil {
		log.Fatal(err)
	}

	// create routes
	routes := api.NewRoutes(redisClient, mongoClient)

	// create fiber app
	app := fiber.New()

	// logger
	app.Use(logger.New())

	// routes
	app.Get("/:url", routes.ResolveUrlHandler)
	app.Post("/shorten", routes.ShortenUrlHandler)

	// start server
	log.Fatal(app.Listen(":3000"))
}
