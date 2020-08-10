package ru.sap.config;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import ru.sap.database.model.Product;
import ru.sap.service.ProductService;

import javax.persistence.EntityManagerFactory;
import java.io.File;


@Configuration
@EntityScan(basePackageClasses = Product.class)
public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    private EntityManagerFactory entityManagerFactory;

    private ProductService productService;

    public Config(EntityManagerFactory entityManagerFactory, ProductService productService) {
        this.entityManagerFactory = entityManagerFactory;
        this.productService = productService;
    }

    @Value("${sourceDir}")
    private String sourceDirectoryPath;
    @Value("${destDir}")
    private String destDirectoryPath;


    @Bean
    public MessageSource<File> source() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(sourceDirectoryPath));
        messageSource.setAutoCreateDirectory(true);
        return messageSource;
    }

//    @Bean
//    public MessageHandler dest() {
//        FileWritingMessageHandler messageHandler = new FileWritingMessageHandler(new File(destDirectoryPath));
//        messageHandler.setExpectReply(false);
//        messageHandler.setDeleteSourceFiles(true);
//        return messageHandler;
//    }


//    @Bean
//    public IntegrationFlow integrationFlow() {
//        return IntegrationFlows.from(source(), conf -> conf.poller(Pollers.fixedDelay(1000)))
//                .filter(msg -> ((File) msg).getName().endsWith(".txt"))
//                .transform(new FileToStringTransformer())
//                .<String, String>transform(String::toUpperCase)
//                .transform(Message.class, message -> {
//                    message.getHeaders().forEach((key, value) -> LOGGER.info("{}", "{}", key, value));
//                    return message;
//                })
//                .handle(dest())
//                .get();
//    }

    @Bean
    public IntegrationFlow integrationFlow() throws Exception{
        return IntegrationFlows.from(source(), conf -> conf.poller(Pollers.fixedDelay(2000)))
                .filter(msg -> ((File) msg).getName().endsWith(".csv"))
                .transform(new FileToStringTransformer())
                .split(s -> s.delimiters("\n"))
                .<String, Product>transform(s -> {
                    String[] strings = s.split(";");
                    Product product = productService.getProductByName(strings[0]);
                    product.setPrice(Integer.parseInt(strings[1]));
                    return product;
                })
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                        .entityClass(Product.class)
                        .persistMode(PersistMode.MERGE),
                        ConsumerEndpointSpec::transactional)
                .get();
    }
}
