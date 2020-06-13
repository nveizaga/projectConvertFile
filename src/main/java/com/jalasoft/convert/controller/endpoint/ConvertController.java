/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.controller.endpoint;

import com.documents4j.api.DocumentType;
import com.jalasoft.convert.controller.component.StorageProperties;
import com.jalasoft.convert.controller.exception.FileException;
import com.jalasoft.convert.model.exception.ConvertException;
import com.jalasoft.convert.model.exception.ParameterInvalidException;
import com.jalasoft.convert.controller.response.ErrorResponse;
import com.jalasoft.convert.controller.response.OkResponse;
import com.jalasoft.convert.controller.service.StorageService;
import com.jalasoft.convert.model.ConvertToPdf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Neida Veizaga
 * @version 1.1
 */
@RestController
@RequestMapping("/api/v1")
public class ConvertController {
    private StorageService storageService;
    private ConvertToPdf convertToPdf;
    private StorageProperties properties;
    private String fileName;

    /**
     * Constructor of Convert object
      * @param storageService storage service for manipulate file for conversation
     */
    public ConvertController(StorageService storageService,ConvertToPdf convertToPdf) {
        this.storageService = storageService;
        this.convertToPdf = convertToPdf;


    }

    /**
     * Method used for convert file to PDF
     * @param file File to convert, MultipartFile
     * @return Response
     */
    @PostMapping("/convert")
    public ResponseEntity convert(
                          @RequestParam(value="file") MultipartFile file) {
        try {
            if(validateFileTypeSupport(file)) {
                this.fileName = storageService.store(file);
                String result = this.convertToPdf.convert(fileName);
                return ResponseEntity.ok().body(
                        new OkResponse<Integer>(result, HttpServletResponse.SC_OK)
                );
            }
            else {
                String invalidFileType = "Invalid file type," + " currently application is only supported: docx,doc,xlsx,xml";
                return ResponseEntity.badRequest().body(
                        new ErrorResponse<Integer>(invalidFileType, HttpServletResponse.SC_BAD_REQUEST)
                );
            }
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ParameterInvalidException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ConvertException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                new ErrorResponse<Integer>(ex.getMessage(),HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }

    /**
     * Method for validate if file type is supported for convert to PDF
     * @param file
     * @return boolean, true: the file type is supported
     */
    public Boolean validateFileTypeSupport(MultipartFile file) {
        int fileNameEndIndex = file.getOriginalFilename().indexOf('.');
        String fileType = file.getOriginalFilename().substring(fileNameEndIndex+1);
        switch (fileType) {
            case "docx":
            case "doc":
            case "xlsx":
            case "xml":
                return true;
            default:
                return false;
        }
    }
}