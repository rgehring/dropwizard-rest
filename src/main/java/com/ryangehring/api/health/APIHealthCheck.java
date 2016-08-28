package com.ryangehring.api.health;

/**
 * Created by ryan on 8/20/16.
 */

import com.codahale.metrics.health.HealthCheck;

public class APIHealthCheck extends HealthCheck {
    private final String template;

    public APIHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}