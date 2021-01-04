package org.mddarr.producer;

import org.springframework.kafka.core.KafkaTemplate;

public interface KafkaTemplateInterface<T> {
    KafkaTemplate<String, T> getKafkaTemplate();
}
