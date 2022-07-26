//package com.ktspace.miniHomeIoT.config;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
////
////@Configuration
////@Lazy//for msa
////@RequiredArgsConstructor
//// 패키지명
////@MapperScan(basePackages = "com.ktspace.miniHomeIoT.mapper")//value = "com.ktspace.miniHomeIoT", sqlSessionFactoryRef = "SqlSessionFactory")
//public class MyBatisConfig {
////
////    @Value("${spring.datasource.mapper-locations}")
////    String mPath;
////
////    @Bean(name = "dataSource")
////    @ConfigurationProperties(prefix = "spring.datasource")
////    public DataSource DataSource() {
////        return DataSourceBuilder.create().build();
////    }
////
////
////    @Bean(name = "SqlSessionFactory")
////    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
////        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
////        sqlSessionFactoryBean.setDataSource(DataSource);
////        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mPath));
////        return sqlSessionFactoryBean.getObject();
////    }
////
////    @Bean(name = "SessionTemplate")
////    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
////        return new SqlSessionTemplate(firstSqlSessionFactory);
////    }
////private final ApplicationContext context;
////
////    @Bean
////    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
////
////        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
////
////        sessionFactory.setDataSource(dataSource);
//////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////
////        sessionFactory.setMapperLocations(
////                context.getResources( // 1
////                        // 실제 쿼리가 들어갈 xml 패키지 경로
////                        "classpath:/mapper/*.xml"
////                ));
////
////        // Value Object를 선언해 놓은 package 경로
////        // Mapper의 result, parameterType의 패키지 경로를 클래스만 작성 할 수 있도록 도와줌.
////        sessionFactory.setTypeAliasesPackage( "com.example.exdocker1.domain" );
////        return sessionFactory.getObject();
////    }
////
////    // Mybatis Template
////    @Bean
////    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
////        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
////
////        sqlSessionTemplate.getConfiguration().setMapUnderscoreToCamelCase(true);
////
//////        sqlSessionTemplate.getConfiguration().setUseGeneratedKeys(true);
////        return sqlSessionTemplate;
////    }
////
////    @Bean
////    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
////        return new DataSourceTransactionManager(dataSource);
////    }
//}