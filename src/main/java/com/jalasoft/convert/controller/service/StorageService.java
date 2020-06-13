/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.controller.service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Neida Veizaga
 * @version 1.1
 */

/**
 * Interface for store service, it was implemented in FileStorageService
 */
public interface StorageService {

    void init();

    String store(MultipartFile file);

    void deleteAll();

}

