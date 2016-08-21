package com.ryangehring.api.utils;

/**
 * Created by ryan on 8/20/16.
 */
public final class ExperimentService {

    public static int assignGroup(Float seed) {
        if (seed <= 0.5) {
            return 1;
        }
        return 2;

    }

}
