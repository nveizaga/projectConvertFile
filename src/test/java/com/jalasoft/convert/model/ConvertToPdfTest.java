/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.model;

import com.jalasoft.convert.controller.component.StorageProperties;
import com.jalasoft.convert.model.exception.ParameterInvalidException;
import com.jalasoft.convert.model.ConvertToPdf;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertToPdfTest {
    private final static String INPUTPATH = "src/test/resources/inputFileTest/";
    private final static String OUTPUTPATH = "src/test/resources/outPutFileTest/";
    StorageProperties properties = new StorageProperties();

    @Test(expected = ParameterInvalidException.class)
    public void convertFileToPDFInvalidParameter() throws Exception {
        properties.setInputFolder(INPUTPATH);
        properties.setOutputFolder(OUTPUTPATH);
        ConvertToPdf convertFileToPdf = new ConvertToPdf(properties);
        convertFileToPdf.convert(null);
    }
    @Test
    public void convertFileToPDFInvalidFileType() throws Exception {
        properties.setInputFolder(INPUTPATH);
        properties.setOutputFolder(OUTPUTPATH);
        ConvertToPdf convertFileToPdf = new ConvertToPdf(properties);
        assertEquals("Invalid file type for convert," + " currently application is only supported: docx,doc,xlsx,xml",
                convertFileToPdf.convert("test.txt"));
    }

    @Test
    public void convertFileDocToPDF() throws Exception {
        String fileToConvert = "Jalasoft Test Case.doc";
        StorageProperties storageProperties = new StorageProperties();
        properties.setInputFolder(INPUTPATH);
        String test = storageProperties.getInputFolder();
        properties.setOutputFolder(OUTPUTPATH);
        ConvertToPdf convertFileToPDF = new ConvertToPdf(properties);
        String result = convertFileToPDF.convert(fileToConvert);
        assertEquals("File: "+ fileToConvert + " was converted to: " + "Jalasoft Test Case" + ".pdf", result);
    }

    @Test
    public void convertFileExcelToPDF() throws Exception {
        String fileToConvert = "excelTest.xlsx";
        StorageProperties storageProperties = new StorageProperties();
        properties.setInputFolder(INPUTPATH);
        String test = storageProperties.getInputFolder();
        properties.setOutputFolder(OUTPUTPATH);
        ConvertToPdf convertFileToPDF = new ConvertToPdf(properties);
        String result = convertFileToPDF.convert(fileToConvert);
        assertEquals("File: "+ fileToConvert + " was converted to: " + "excelTest" + ".pdf", result);
    }


}