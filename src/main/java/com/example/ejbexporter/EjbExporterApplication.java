package com.example.ejbexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@SpringBootApplication(exclude = { JmxAutoConfiguration.class})
public class EjbExporterApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EjbExporterApplication.class);
	}

	public static void main(String[] args) {


		SpringApplication.run(EjbExporterApplication.class, args);
		System.out.println("Application started successfully.");
	}

	@Bean
	public DataSource dataSource() {
		try {
			System.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
			InitialContext context = new InitialContext();
			// Use the full JNDI name including the application and module names
			return (DataSource) context.lookup("jdbc/ekpDataSource");
		} catch (NamingException e) {
			throw new RuntimeException("Failed to lookup DataSource", e);
		}
	}


	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
