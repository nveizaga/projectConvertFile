/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package com.jalasoft.convert.model.exception;

/**
 * @author Neida Veizaga
 * @version 1.1
 */

/**
 * Exception error displayed during converting file
 */
public class ConvertException extends Exception {

    private static final String MESSAGE = "Error converting file.";

    public ConvertException(String currentMessage, Throwable ex) {
        super(currentMessage, ex);
    }

    public ConvertException(Throwable ex) {
        super(MESSAGE, ex);
    }

}
