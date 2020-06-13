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
public class ConvertToPdfParam extends Parameter {
    private File outPutFile;
    public ConvertToPdfParam(File inputFile,File outPutFile) {
        super(inputFile);
        this.outPutFile = outPutFile;
    }
    public File getOutPutFile() {
        return this.outPutFile;
    }
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }
    public void setOutPutFile(File outPutFile) {
        this.outPutFile = outPutFile;
    }
    @Override
    public void validate() throws ParameterInvalidException {
        super.validate();
        if (this.inputFile.isHidden()) {
            throw new ParameterInvalidException();
        }
        if (this.inputFile == null) {
            throw new ParameterInvalidException();
        }
        if(this.outPutFile.isHidden()){
            throw new ParameterInvalidException();
        }

    }

}
