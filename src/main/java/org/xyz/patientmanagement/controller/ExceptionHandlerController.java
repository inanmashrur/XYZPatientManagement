package org.xyz.patientmanagement.controller;

import org.xyz.patientmanagement.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;

import static org.xyz.patientmanagement.util.Constants.MSG;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    private static final String UNAUTHORIZED_PAGE = "unauthorized";

    private static final String ERROR_PAGE = "error";

    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(HttpServletRequest request) {
        logger.warn("Unauthorized Access | url: " + request.getServletPath() + " | UserID: ");

        return UNAUTHORIZED_PAGE;
    }

    @ExceptionHandler(OptimisticLockException.class)
    public String handleOptimisticLockException(HttpServletRequest request) {
        logger.debug("Optimistic Lock | url: " + request.getServletPath() + " | UserID: ");

        return ERROR_PAGE;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleExceptions(Exception exception) {
        logger.error(exception.getClass() + " | ", exception);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ERROR_PAGE);
        modelAndView.addObject(MSG, exception.getMessage());

        return modelAndView;
    }
}
