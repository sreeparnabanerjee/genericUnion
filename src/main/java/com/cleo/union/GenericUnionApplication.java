package com.cleo.union;

import com.cleo.union.resources.GenericUnionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GenericUnionApplication extends Application<GenericUnionConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GenericUnionApplication().run(args);
    }

    @Override
    public String getName() {
        return "GenericUnion";
    }

    @Override
    public void initialize(final Bootstrap<GenericUnionConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final GenericUnionConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new GenericUnionResource());
    }

}
