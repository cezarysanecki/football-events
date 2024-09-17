package org.djar.football.stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.djar.football.model.event.Event;
import org.djar.football.util.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    private final KafkaProducer<String, Event> producer;

    public EventPublisher(KafkaProducer<String, Event> producer) {
        this.producer = producer;
    }

    public Mono<Void> fire(Event event) {
        return Mono.create(sink -> {
            String topic = Topics.eventTopicName(event.getClass());
            ProducerRecord<String, Event> record = new ProducerRecord<>(
                    topic,
                    0,
                    event.getTimestamp().toEpochMilli(),
                    event.getAggId(),
                    event);

            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    sink.success();
                    logger.debug("New {} event created: {}", event.getClass().getSimpleName(), event.getAggId());
                } else {
                    sink.error(exception);
                }
            });
        });
    }

}
