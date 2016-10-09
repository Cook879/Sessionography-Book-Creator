package uk.me.richardcook.sessionography;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig {

	@Bean( name = "dataSource" )
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName( "com.mysql.jdbc.Driver" );
		dataSource.setUrl( "jdbc:mysql://localhost:3306/sinatra_book_2" );
		dataSource.setUsername( "root" );
		dataSource.setPassword( "" );
		return dataSource;
	}

	@Bean( name = "entityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean getEntityManager( DataSource dataSource ) {

		LocalContainerEntityManagerFactoryBean entityManagerBuilder = new LocalContainerEntityManagerFactoryBean();

		entityManagerBuilder.setDataSource( dataSource );
		entityManagerBuilder.setPackagesToScan( "uk.me.richardcook.sessionography.model" );

		entityManagerBuilder.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect" );
		jpaProperties.setProperty( "hibernate.show_sql", "true" );
		entityManagerBuilder.setJpaProperties( jpaProperties );

		return entityManagerBuilder;
	}

	@Bean( name = "transactionManager" )
	public PlatformTransactionManager getTransactionManager( EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager( entityManagerFactory );
	}
}