/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.model;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.jalasoft.convert.controller.component.StorageProperties;
import com.jalasoft.convert.model.exception.ParameterInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.jalasoft.convert.model.parameter.ConvertToPdfParam;
import com.jalasoft.convert.model.exception.ConvertException;


/**
 * @author Neida Veizaga
 * @version 1.1
 */
@Service
public class  ConvertToPdf implements Convert  {
    private Path rootLocation;
    private Path rootOutputLocation;
    private StorageProperties properties;

    @Autowired
    public ConvertToPdf(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getInputFolder());
        this.rootOutputLocation = Paths.get(properties.getOutputFolder());
    }

    /**
     * Method used for convert a some file type to pdf file
     * @param fileToConvert file to convert
     * @return path or invalid message for support invalid file type
     * @throws Exception
     */
    @Override
      public String convert(String fileToConvert) throws Exception {
        String fileName;
        String fileType;
        ConvertToPdfParam param;
        Boolean fileIsSupported = Boolean.TRUE;
        int fileNameEndIndex;
        try  {
            File inputFile = new File( this.rootLocation + "//"+ fileToConvert);
            fileNameEndIndex = fileToConvert.indexOf('.');
            fileName = fileToConvert.substring(0,fileNameEndIndex);
            fileType = fileToConvert.substring(fileNameEndIndex+1);
            File outputFile = new File(this.rootOutputLocation + "//"+ fileName + ".pdf");
            param = new ConvertToPdfParam(inputFile, outputFile);
            param.validate();
            InputStream fileInputStream = null;
            OutputStream outputStream = null;
            IConverter converter;
            fileInputStream = new FileInputStream(inputFile);
            outputStream = new FileOutputStream(outputFile);
            converter = LocalConverter.builder().build();
            switch (fileType) {
                case "docx":
                    converter.convert(fileInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
                    break;
                case "doc":
                    converter.convert(fileInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
                    break;
                case "xlsx":
                    converter.convert(fileInputStream).as(DocumentType.XLSX).to(outputStream).as(DocumentType.PDF).execute();
                    break;
                case "xml":
                    converter.convert(fileInputStream).as(DocumentType.XML).to(outputStream).as(DocumentType.PDF).execute();
                    break;
                default:
                    fileIsSupported = Boolean.FALSE;
                    break;
            }

            outputStream.close();
            if(fileIsSupported) {
                String successMessage = "File: "+ fileToConvert + " was converted to: " + fileName + ".pdf" ;
                return successMessage;
            }
            else {
                String invalidFile = "Invalid file type for convert," + " currently application is only supported: docx,doc,xlsx,xml";
                return invalidFile;
            }

        } catch (NullPointerException ex) {
            throw new ParameterInvalidException(ex);
        }catch (Throwable ex) {
            throw new ConvertException(ex);
        }

    }

}