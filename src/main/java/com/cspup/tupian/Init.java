package com.cspup.tupian;


import com.cspup.tupian.common.DatabaseInit;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author csp
 * @date 2022/3/25 18:14
 * @description ApplicationContext初始化完成后进行应用初始化操作
 */
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
//    由于DatabaseInit中使用了@Value注解，需要使用Spring注入的方式使用对象。直接new对象@Value取不到值
    final private DatabaseInit dbInit;

    public Init(DatabaseInit dbInit) {
        this.dbInit = dbInit;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            dbInit.init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
