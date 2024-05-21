package br.unisales.tarefas.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * configuranção necessária para usar indices no mongodb
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
 @Override
 protected boolean autoIndexCreation() {
  return true;
 }

 @Override
 protected String getDatabaseName() {
  return "demosbsec";
 }

}
