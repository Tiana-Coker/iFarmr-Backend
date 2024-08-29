package org.ifarmr.utils;

import lombok.experimental.UtilityClass;
import org.ifarmr.exceptions.FunctionErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUploadUtil {

    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024; //About 5MB

    //Acceptable image type
    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile( pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long fileSize = file.getSize();
        if (fileSize > MAX_FILE_SIZE) {
            throw new FunctionErrorException("Max file size is 5MB");
        }
        final String fileName = file.getOriginalFilename();
        if (!isAllowedExtension(fileName, pattern)) {
            throw new FunctionErrorException("Only jpg, png, gif, bmp, jpeg files are supported");
        }
    }

}
