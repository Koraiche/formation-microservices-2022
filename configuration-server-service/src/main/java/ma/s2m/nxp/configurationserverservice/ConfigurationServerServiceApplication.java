package ma.s2m.nxp.configurationserverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServerServiceApplication.class, args);
	}

}
