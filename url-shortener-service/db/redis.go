package db

import (
	"github.com/redis/go-redis/v9"
	"os"
	"strconv"
)

type RedisClient struct {
	Client *redis.Client
}

func NewRedisClient() (*RedisClient, error) {
	addr := os.Getenv("REDIS_ADDR")
	if addr == "" {
		addr = "localhost:6379"
	}

	pass := os.Getenv("REDIS_PASSWORD")
	if pass == "" {
		pass = ""
	}

	db, err := strconv.Atoi(os.Getenv("REDIS_DB"))
	if err != nil {
		return nil, err
	}

	client := redis.NewClient(&redis.Options{
		Addr:     addr,
		Password: pass,
		DB:       db,
	})

	return &RedisClient{Client: client}, nil
}
