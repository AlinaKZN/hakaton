package ru.povolzie.hakaton.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import ru.povolzie.hakaton.model.geodata.GeoData;
import ru.povolzie.hakaton.service.GeoDataService;

@Slf4j
@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfig {

  @Autowired
  GeoDataService geoDataService;

  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
  public KafkaStreamsConfiguration kStreamsConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "0");//0?
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "gpbtask.fun:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    return new KafkaStreamsConfiguration(props);
  }

  @Bean
  public Serde<GeoData> userSerde() {
    return Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(GeoData.class));
  }

  @Bean
  public KStream<String, GeoData> kStream(StreamsBuilder kStreamBuilder) {
    KStream<String, String> stream = kStreamBuilder
        .stream("input1", Consumed.with(Serdes.String(), Serdes.String()));
    KStream<String, GeoData> userStream = stream
        .mapValues(this::getFromString);
    //  .filter((key, value) -> value.getBalance() <= 0);
    userStream.to("out", Produced.with(Serdes.String(), userSerde()));
    return userStream;
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  GeoData getFromString(String str) {
    GeoData geodata = null;
    try {
      geodata = objectMapper().readValue(str, GeoData.class);
      geoDataService.create(geodata);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }
    return geodata;
  }
}