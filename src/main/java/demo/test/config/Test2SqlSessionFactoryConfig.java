package demo.test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolver;

import javax.sql.DataSource;
import java.io.InputStream;

/**
 * @author wenxiangmin
 * @ClassName TestSqlSessionFactoryConfig.java
 * @Description TODO
 * @createTime 2022年06月09日 13:16:00
 */
@Configuration
@MapperScan(value = "demo.test2.mapper",sqlSessionFactoryRef = "test2SqlSessionFactory")
public class Test2SqlSessionFactoryConfig {
    @Autowired
    @Qualifier(value = "test2DataSource")
    private DataSource dataSource;
    @Bean
    public SqlSessionFactoryBean test2SqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resourceResolver.getResources("mapper/test2/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean;
    }
}
