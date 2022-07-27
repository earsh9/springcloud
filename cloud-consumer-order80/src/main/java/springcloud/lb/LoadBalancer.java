package springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    //1. 收集 Eureka 上所有活跃着的服务信息 (参照 discoveryClient.getInstances() )
    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
