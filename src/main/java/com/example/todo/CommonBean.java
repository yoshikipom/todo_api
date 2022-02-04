package com.example.todo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBean {

  private List<Converter<?, ?>> converters;

  public CommonBean(List<Converter<?, ?>> converters) {
    this.converters = converters;
  }

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    for (Converter<?, ?> converter : converters) {
      modelMapper.addConverter(converter);
    }
    return modelMapper;
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }
}
