package com.lavrisha.tracker;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.codegen.MetaDataExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

@SpringBootApplication
public class GenerateSqlClasses {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(GenerateSqlClasses.class, args);

        DataSource dataSource = context.getBeanFactory().getBean(DataSource.class);
        Configuration configuration = context.getBeanFactory().getBean(Configuration.class);

        MetaDataExporter exporter = new MetaDataExporter();
        exporter.setPackageName("com.lavrisha.tracker");
        exporter.setTargetFolder(new File("src/querydsl/java"));
        exporter.setNamePrefix("Sql");
        exporter.setConfiguration(configuration);
        exporter.export(dataSource.getConnection().getMetaData());

        context.close();
    }
}
