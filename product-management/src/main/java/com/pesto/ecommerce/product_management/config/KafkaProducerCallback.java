package com.pesto.ecommerce.product_management.config;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.bson.json.JsonObject;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class KafkaProducerCallback<T> implements Callback {
    private T message;
    private String topic;



    public KafkaProducerCallback(T message, String topic) {
        this.message = message;
        this.topic = topic;
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {

        if (exception == null) {
        } else {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            String stackTrace = sw.toString();

            // logger.error(String.format("Failed to publish message : %s to topic : %s",this.message, this.topic),exception);
            JSONObject object = new JSONObject(message);
            object.put("metadata", metadata);
            object.put("exception", stackTrace);

        }
    }
}
