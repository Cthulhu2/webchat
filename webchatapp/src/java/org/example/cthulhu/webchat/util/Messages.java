package org.example.cthulhu.webchat.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.MessageSource;
/**
 *
 * @author Cthulhu
 */
public class Messages {
    
    public static String getMessage(MessageSource ms, String message, Object... os) {
        return ms.getMessage(message, os, LocaleContextHolder.getLocale());
    }
}
