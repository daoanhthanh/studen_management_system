package hanu.edu.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsApplication implements CommandLineRunner {

    private final InitData initData;

    @Autowired
    public EmsApplication(InitData initData) {
        this.initData = initData;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initData.init();
    }
}
