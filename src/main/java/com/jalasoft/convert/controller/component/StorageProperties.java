/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.controller.component;

/**
 * @author Neida Veizaga
 * @version 1.1
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "convert")
public class StorageProperties {

    private String inputFolder;
    private String outputFolder;

    /**
     * Return inputFolder where files should be stored
     * @return inputFolder
     */
    public String getInputFolder() {
        return this.inputFolder;
    }

    /**
     * Set input folder to store files
     * @param inputFolder folder path
     */
    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    /**
     * Return outputFolder where present the files converted
     * @return outputFolder
     */
    public String getOutputFolder() {
        return this.outputFolder;
    }

    /**
     * Set output folder to save files converted
     * @param outputFolder folder path
     */
    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

}

