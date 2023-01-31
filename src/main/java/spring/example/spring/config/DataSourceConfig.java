package spring.example.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {
    private final DataSource dataSource;
    @Bean
    public DataSourceInitializer initializer() throws IOException {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator() throws IOException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        Resource resource = new ClassPathResource("data.sql");
        if (resource.exists() && resource.isReadable() && resource.contentLength() > 0) {
            populator.addScript(resource);
        }
        return populator;
    }

}
