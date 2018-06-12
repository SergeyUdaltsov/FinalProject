package com.configuration;

import com.domain.quartz.ComplexJob;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.quartz.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com")
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories("com.repository")
public class ApplicationContext implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        manager.setDataSource(dataSource());
        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(adapter);
        emfb.setPackagesToScan("com.domain");
        return emfb;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor postProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/advertisement");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }



    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }

//    @Bean
//    public JavaMailSenderImpl mailSender() {
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setHost("smtp.gmail.com");
//        sender.setPort(587);//465
//        sender.setUsername("Udalcov42@gmail.com");
//        sender.setPassword("t883774");
//
//
//        Properties properties = sender.getJavaMailProperties();
//        properties.put("mail.transport.protocol", "smtp");//g
//        properties.setProperty("mail.smpt.auth", "true");
//        properties.setProperty("mail.smpt.starttls.enable", "true");
//
//        return sender;
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);//This will make all such beans accessible in plain ${...} expressions in a JSP 2.0 page
        return resolver;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactory() {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();

        obj.setTargetBeanName("myJob");
        obj.setTargetMethod("run");

        return obj;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactory() {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();

        factoryBean.setJobDetail(methodInvokingJobDetailFactory().getObject());
        factoryBean.setStartDelay(3000);
        factoryBean.setRepeatInterval(3000);
        return factoryBean;
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactory() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(ComplexJob.class);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "John");

        jobDetail.setJobDataAsMap(map);

        jobDetail.setGroup("mygroup");
        jobDetail.setName("newJob");

        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactory() {
        CronTriggerFactoryBean cron = new CronTriggerFactoryBean();

        cron.setJobDetail(jobDetailFactory().getObject());

        cron.setStartDelay(3000);

        cron.setName("MyCronTrigger");
        cron.setGroup("mygroup");
        cron.setCronExpression("0 0/1 * 1/1 * ? *");

        return cron;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        factoryBean.setTriggers(simpleTriggerFactory().getObject(), cronTriggerFactory().getObject());

        return factoryBean;
    }
}
