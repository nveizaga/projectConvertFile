/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.controller.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.jalasoft.convert.controller.component.StorageProperties;
import com.jalasoft.convert.controller.response.ErrorResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Neida Veizaga
 * @version 1.1
 */
@RestController
@RequestMapping("/api/v1")
public class DownloadController {
    @Autowired
    private StorageProperties properties;

    /**
     * Get request for donwload file converted
     * @param fileName File name for donwload
     * @return Response
     */
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFile(@PathVariable("fileName") String fileName) {
        try {
            String commonContentType = "application/pdf";
            Path path = Paths.get(properties.getOutputFolder() + fileName);
            Resource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(commonContentType))
                    .body(resource);
        } catch (MalformedURLException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }
}
