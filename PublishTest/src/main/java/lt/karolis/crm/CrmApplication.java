package lt.karolis.crm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//@SpringBootApplication
@EnableSwagger2
//@EnableWebMvc
@ComponentScan (basePackages = "lt.karolis.crm")
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean em =
				new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("lt.karolis.crm");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		return em;
	};

//	@Bean
//	@ConditionalOnProperty(
//			name = "usemysql",
//			havingValue = "local")
//	@ConditionalOnMissingBean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");
//		dataSource.setUsername("mysqluser");
//		dataSource.setPassword("mysqlpass");
//
//		return dataSource;

	@Bean
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/task_manager");
		config.setUsername("task");
		config.setPassword("task");
		config.setMaximumPoolSize(5);
		return new HikariDataSource(config);
	}
}
