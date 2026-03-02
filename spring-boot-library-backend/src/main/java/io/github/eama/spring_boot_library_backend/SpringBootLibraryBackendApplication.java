package io.github.eama.spring_boot_library_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class SpringBootLibraryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLibraryBackendApplication.class, args);
	}

}
