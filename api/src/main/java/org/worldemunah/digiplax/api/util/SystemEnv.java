package org.worldemunah.digiplax.api.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * User: Ariel
 * Date: 5/19/2019
 */
public class SystemEnv {

    public static String getOutputDirFilePath() {
        String dirFilePath = System.getProperty("digiplax.out.dir");
        if (StringUtils.isNotEmpty(dirFilePath)) {
            return dirFilePath;
        }

        return FilenameUtils.concat(System.getProperty("user.dir"), "out");
    }
}
