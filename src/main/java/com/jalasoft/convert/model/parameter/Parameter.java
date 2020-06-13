/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.model.parameter;

import java.io.File;
import com.jalasoft.convert.model.exception.ParameterInvalidException;

/**
 * @author Neida Veizaga
 * @version 1.1
 */
public class Parameter {
    File inputFile;

    public Parameter(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void validate() throws ParameterInvalidException {
        if(inputFile.isHidden()) {
            throw new ParameterInvalidException();
        }
        if(!inputFile.isFile()) {
            throw new ParameterInvalidException();
        }
    }
}
