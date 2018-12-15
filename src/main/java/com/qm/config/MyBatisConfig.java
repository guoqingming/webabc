package com.qm.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis基础配置
 *
 * @author liuzh
 * @since 2015-12-19 10:11
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

  @Autowired
  DataSource dataSource;

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setTypeAliasesPackage("com.qm.mapper");

    // 分页插件
    PageInterceptor pageHelper = new PageInterceptor();
    Properties properties = new Properties();
    properties.setProperty("reasonable", "true");
    properties.setProperty("supportMethodsArguments", "true");
    properties.setProperty("returnPageInfo", "check");
    properties.setProperty("params", "count=countSql");
    pageHelper.setProperties(properties);

    // 添加XML目录
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//    Interceptor[] plugins = new Interceptor[] { pageHelper };
//
//    bean.setPlugins(plugins);
//    try {
//      bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//      return bean.getObject();
//    } catch (Exception e) {
//      e.printStackTrace();
//      throw new RuntimeException(e);
//    }
    return bean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }
}
