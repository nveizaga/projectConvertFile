/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.controller.exception;

/**
 * @author Neida Veizaga
 * @version 1.1
 */
public class FileException extends Exception {
    public FileException(String message, Throwable ex) {
        super(message, ex);
    }

    public FileException(String message) {
        super(message);
    }
}
