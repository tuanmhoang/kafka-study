# kafka-study
My study about Kafka

Kafka using docker can be found at `bitnami-kafka` and `confluent-kafka` directories.

## 1. bitnami-kafka

- To start

`docker compose -f kafka-single-node.yml up -d`

- To access to bash

`docker exec -it kafka-broker /bin/bash`

- The bin is located at

`/opt/bitnami/kafka/bin`

- To start kafka

`kafka-server-start.sh`

- To stop kafka

`kafka-server-stop.sh`

- To create topics

`./kafka-topics.sh --create --topic kafka.learning.tweets --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`

`./kafka-topics.sh --create --topic kafka.learning.alert --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`

- To list topics

`./kafka-topics.sh --bootstrap-server localhost:9092 --list`

- To describe topics

`./kafka-topics.sh --bootstrap-server localhost:9092 --describe`

- To send message

`./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic kafka.learning.tweets`

- To consume message

`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kafka.learning.tweets`

or 

`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kafka.learning.tweets --from-beginning`

## 2. bitnami-zookeper

- To login ZooKeeper

`docker exec -it zookeeper /bin/bash`

- To go to bin directory

`cd /opt/bitnami/zookeeper/bin/`

- To connect ZooKeeper

`zkCli.sh`

- To list

`ls /`

`ls /brokers`

`ls /brokers/ids`

`ls /brokers/topics`