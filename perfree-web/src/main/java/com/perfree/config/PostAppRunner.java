package com.perfree.config;

import com.jfinal.template.Directive;
import com.perfree.common.OptionCache;
import com.perfree.directive.DirectiveUtil;
import com.perfree.directive.TemplateDirective;
import com.perfree.mapper.OptionMapper;
import com.perfree.model.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Execute after startup
 *
 * @author Perfree
 */
@Component
public class PostAppRunner implements ApplicationRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostAppRunner.class);

    private final OptionMapper optionMapper;

    public PostAppRunner(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    @Override
    public void run(ApplicationArguments args) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/perfree?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("215607..");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        DataSource dataSource = dataSourceBuilder.build();
        DynamicDataSource.setDataSource(dataSource);
        // Load options and put into memory
        if (DynamicDataSource.getDataSource() != null) {
            List<Option> options = optionMapper.getStartOption();
            options.forEach(r -> OptionCache.setOption(r.getKey(), r.getValue()));
            // Load Template Directive
            PostAppRunner.loadDirective();
        }
    }

    /**
     * Load Template Directive
     */
    public static void loadDirective() {
        Map<String, Object> beans = DirectiveUtil.getBean();
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            TemplateDirective injectBean = bean.getClass().getAnnotation(TemplateDirective.class);
            Directive directive = (Directive) bean;
            Class<? extends Directive> directiveByName = EnjoyConfig.jfr.getEngine().getEngineConfig().getDirective(injectBean.value());
            if (directiveByName == null) {
                LOGGER.info("Add Directive: {}", injectBean.value());
                EnjoyConfig.jfr.addDirective(injectBean.value(), directive.getClass());
            }
        }
    }
}
