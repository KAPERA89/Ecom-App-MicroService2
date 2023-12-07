package ma.xproce.inventoryservice;

import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import ma.xproce.inventoryservice.repositories.CreatorRepository;
import ma.xproce.inventoryservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository){
        return args -> {

          Creator c = Creator.builder().name("Othmane").email("othmane@gmail.com").build();
          Creator c2 = Creator.builder().name("Othmane2").email("othmane2@gmail.com").build();
          Creator c3 = Creator.builder().name("Othmane3").email("othmane3@gmail.com").build();
          creatorRepository.saveAll(List.of(c,c2,c3));



            Video v = Video.builder().name("video1").url("urlvideo1").description("descriptionVideo1").datePublication(new Date()).creator(c).build();
            Video v2 = Video.builder().name("video2").url("urlvideo2").description("descriptionVideo2").datePublication(new Date()).creator(c2).build();
            Video v3 = Video.builder().name("video3").url("urlvideo3").description("descriptionVideo3").datePublication(new Date()).creator(c3).build();

            videoRepository.saveAll(List.of(v,v2,v3));

            c.setVideoList(List.of(v,v2));
            c2.setVideoList(List.of(v2,v3));
            c3.setVideoList(List.of(v,v3));

        };
    }

}
