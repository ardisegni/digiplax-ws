package org.worldemunah.digiplax.api.model;

import java.io.Serializable;

/**
 * User: Ariel
 * Date: 5/20/2019
 */
public class StreamingModel implements Serializable {

    private String plaqueText;
    private String plaqueHtmlText;
    private String backgroundImgPath;

    protected StreamingModel() {
    }

    public StreamingModel(String plaqueText, String plaqueHtmlText) {
        this.plaqueText = plaqueText;
        this.plaqueHtmlText = plaqueHtmlText;
    }

    public String getPlaqueText() {
        return plaqueText;
    }

    public void setPlaqueText(String plaqueText) {
        this.plaqueText = plaqueText;
    }

    public String getPlaqueHtmlText() {
        return plaqueHtmlText;
    }

    public void setPlaqueHtmlText(String plaqueHtmlText) {
        this.plaqueHtmlText = plaqueHtmlText;
    }

    public String getBackgroundImgPath() {
        return backgroundImgPath;
    }

    public void setBackgroundImgPath(String backgroundImgPath) {
        this.backgroundImgPath = backgroundImgPath;
    }
}
