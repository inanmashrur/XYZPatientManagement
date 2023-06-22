package org.xyz.patientmanagement.util;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xyz.patientmanagement.domain.Assistant;
import org.xyz.patientmanagement.domain.Doctor;
import org.xyz.patientmanagement.domain.User;
import org.xyz.patientmanagement.model.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.xyz.patientmanagement.util.Constants.*;

/**
 * @author inanmashrur
 * @since 04/04/2023
 */
public class Util {

    private static final String MSG_PAGE = "msg";

    public static boolean isPathAllowedToAll(String path) {
        List<String> allowedPaths = Arrays.asList("/", "/login", "/auth/login");

        return path.startsWith("/resources/") || allowedPaths.contains(path);
    }

    public static boolean isNonNull(Object... objects) {
        for (Object obj : objects) {
            if (isNull(obj)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidIdentifier(Long id) {
        return nonNull(id) && id != 0;
    }

    public static boolean isParsableIdentifier(String text) {
        boolean parsable = true;

        try {
            if (Long.parseLong(text) == 0) {
                parsable = false;
            }
        } catch (Exception e) {
            parsable = false;
        }

        return parsable;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return nonNull(session.getAttribute(TOKEN));
    }

    public static HttpServletRequest getRequestObject() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return requestAttributes.getRequest();
    }

    public static long getLoggedInUserId() {
        return getLoggedInUser().getId();
    }

    public static User getLoggedInUser() {
        return ((Token) getRequestObject().getSession().getAttribute(TOKEN)).getUser();
    }

    public static Doctor getLoggedInDoctor() {
        return ((Token) getRequestObject().getSession().getAttribute(TOKEN)).getDoctor();
    }

    public static Assistant getLoggedInAssistant() {
        return ((Token) getRequestObject().getSession().getAttribute(TOKEN)).getAssistant();
    }

    public static String getHashedValue(String password) throws NoSuchAlgorithmException {
        if (isNull(password)) {
            return null;
        }

        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password.getBytes());

        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static String redirectToMsgPage(RedirectAttributes redirectAttributes,
                                           MessageSourceAccessor msa,
                                           String type,
                                           String code,
                                           String codeParameter) {

        redirectAttributes.addFlashAttribute(TYPE, type);
        redirectAttributes.addFlashAttribute(MSG, msa.getMessage(code, new Object[]{codeParameter}));

        return redirectTo(MSG_PAGE);
    }

    public static String redirectTo(String url) {
        return "redirect:/" + url;
    }
}
