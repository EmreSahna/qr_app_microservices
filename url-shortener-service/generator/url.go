package generator

const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

var counter int

const length = 7

func GenerateId() string {
	id := make([]byte, length)
	for i := range id {
		id[i] = '0'
	}

	temp := counter
	for i := 0; i < length; i++ {
		index := temp % len(charset)
		temp = temp / len(charset)
		id[i] = charset[index]
	}

	counter++
	return string(id)
}
