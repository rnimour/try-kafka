# try-kafka

We follow [Baeldung's Apache Kafka guide](https://www.baeldung.com/apache-kafka).

We assume Kafka is already running. Simplest way to do it using docker (from [Kafka official docs](https://kafka.apache.org/quickstart)):

> Get the docker image:
> ```
> docker pull apache/kafka:3.7.0
> ```
> Start the kafka docker container
> ```
> docker run -p 9092:9092 apache/kafka:3.7.0
> ```

Kafka should now be running.

The simplest way to run  `Subscriber.kt` and `Publisher.kt` from IntelliJ. 

Then simply start sending messages through the command line in `Publisher.kt`, 
and see them appear in the `Subscriber.kt` command line window.
