package demo.test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wenxiangmin
 * @ClassName TestSqlSessionFactoryConfig.java
 * @Description TODO
 * @createTime 2022年06月09日 13:16:00
 */
@Configuration
@MapperScan(value = "demo.test.mapper",sqlSessionFactoryRef = "testSqlSessionFactory")
public class TestSqlSessionFactoryConfig {
    @Autowired
    @Qualifier(value = "testDataSource")
    private DataSource dataSource;


    @Bean
    @Primary
    public SqlSessionFactoryBean testSqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resourceResolver.getResources("mapper/test/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean;
    }
}
