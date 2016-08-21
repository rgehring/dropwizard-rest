package com.ryangehring.api;

/**
 * Created by ryan on 8/20/16.
 */
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.ryangehring.api.resources.HelloResource;
import com.ryangehring.api.health.TemplateHealthCheck;

public class APIApplication extends Application<APIConfiguration>{

    public static void main(String[] args) throws Exception {
        new APIApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<APIConfiguration> bootstrap) {
        //bootstrap.setName("hello-world");
    }

    @Override
    public void run(APIConfiguration configuration,
                    Environment environment) {
        final HelloResource resource = new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }


}
