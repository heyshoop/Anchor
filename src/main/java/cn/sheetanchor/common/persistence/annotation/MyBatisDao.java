package cn.sheetanchor.common.persistence.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author 阁楼麻雀
 * @Date 2017/4/27 15:01
 * @Desc 标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisDao {
	
	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any
	 */
	String value() default "";

}