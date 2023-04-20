# Simple Kafka Spring Boot application

## 1. Use case:

![image](https://user-images.githubusercontent.com/37680968/233305751-6e59d1f7-0c50-4c7f-8261-b20f55ea23ec.png)

The idea is to have a controller to trigger single message producer and batch messages producer

- To send single message: http://localhost:8080/single/<YourMessageHere>

- To send batch: http://localhost:8080/batch/<YourMessageHere>

## 2. Notes

### Batch producer

- https://www.conduktor.io/kafka/kafka-producer-batching/
  
> Increase `linger.ms` and the producer will wait a few milliseconds for the batches to fill up before sending them.
> 
> If you are sending full batches and have memory to spare, you can increase `batch.size` and send larger batches.
git a