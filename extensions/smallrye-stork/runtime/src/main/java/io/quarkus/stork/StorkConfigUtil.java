package io.quarkus.stork;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.smallrye.stork.api.config.ServiceConfig;
import io.smallrye.stork.spi.config.SimpleServiceConfig;

public class StorkConfigUtil {

    public static List<ServiceConfig> toStorkServiceConfig(StorkConfiguration storkConfiguration) {
        List<ServiceConfig> storkServicesConfigs = new ArrayList<>();
        Set<String> servicesConfigs = storkConfiguration.serviceConfiguration.keySet();
        SimpleServiceConfig.Builder builder = new SimpleServiceConfig.Builder();
        for (String serviceName : servicesConfigs) {
            builder.setServiceName(serviceName);
            ServiceConfiguration serviceConfiguration = storkConfiguration.serviceConfiguration.get(serviceName);
            SimpleServiceConfig.SimpleConfigWithType storkServiceDiscoveryConfig = new SimpleServiceConfig.SimpleConfigWithType(
                    serviceConfiguration.serviceDiscovery.type, serviceConfiguration.serviceDiscovery.params);
            builder = builder.setServiceDiscovery(storkServiceDiscoveryConfig);
            SimpleServiceConfig.SimpleLoadBalancerConfig loadBalancerConfig = new SimpleServiceConfig.SimpleLoadBalancerConfig(
                    serviceConfiguration.loadBalancer.type, serviceConfiguration.loadBalancer.parameters);
            builder.setLoadBalancer(loadBalancerConfig);
            storkServicesConfigs.add(builder.build());
        }
        return storkServicesConfigs;
    }

}
