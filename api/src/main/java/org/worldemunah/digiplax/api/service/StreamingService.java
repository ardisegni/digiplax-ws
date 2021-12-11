package org.worldemunah.digiplax.api.service;

import org.worldemunah.digiplax.api.model.StreamingModel;

import java.util.List;

/**
 * User: Ariel
 * Date: 10/4/2019
 */
public interface StreamingService {

    List<StreamingModel> getStreamingPlaques(String projectUrlParam);

    List<StreamingModel> getScheduledPlaques(String project);
}
