package com.ryangehring.api;

/**
 * Created by ryan on 8/20/16.
 */
import com.bendb.dropwizard.redis.JedisFactory;
import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.ryangehring.api.utils.AuthService;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.elasticsearch.config.EsConfiguration;
import io.dropwizard.elasticsearch.health.EsClusterHealthCheck;
import io.dropwizard.elasticsearch.managed.ManagedEsClient;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.bendb.dropwizard.redis.JedisBundle ;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

import java.security.Principal;
import java.util.Optional;

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
        bootstrap.addBundle(new JedisBundle<APIConfiguration>() {
            @Override
            public JedisFactory getJedisFactory(APIConfiguration configuration) {
                return configuration.getJedisFactory();
            }
        });
    }

    @Override
    public void run(APIConfiguration configuration,
                    Environment environment) throws Exception {

        // set up jwt
        final byte[] key = configuration.getJwtTokenSecret();
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance
        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter<>().Builder<MyUser>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new AuthService())
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider<>().Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //environment.jersey().register(new SecuredResource(configuration.getJwtTokenSecret()));


        // set up elasticsearch
        final ManagedEsClient managedClient = new ManagedEsClient(configuration.getEsConfiguration());
        environment.lifecycle().manage(managedClient);
        environment.healthChecks().register("ES cluster health", new EsClusterHealthCheck(managedClient.getClient()));

        // set up redis


    }


}
