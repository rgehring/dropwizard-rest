package com.ryangehring.api;

/**
 * Created by ryan on 8/20/16.
 */
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.ryangehring.api.resources.SayingResource;
import com.ryangehring.api.health.TemplateHealthCheck;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI ;
import com.ryangehring.api.database.SayingDAO ;
import com.ryangehring.api.core.Saying ;


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

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final SayingDAO dao = jdbi.onDemand(SayingDAO.class);
        final SayingResource sayingResource = new SayingResource( dao ) ;

        environment.jersey().register( sayingResource  );


    }


}
