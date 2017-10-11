package com.lavrisha.tracker;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import com.querydsl.sql.types.JSR310LocalDateType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public com.querydsl.sql.Configuration queryDslConfiguration() {
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(H2Templates.DEFAULT);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        configuration.register(new JSR310LocalDateType());
        return configuration;
    }

    @Bean
    public SQLQueryFactory sqlQueryFactory(com.querydsl.sql.Configuration configuration, DataSource dataSource) {
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }
}
