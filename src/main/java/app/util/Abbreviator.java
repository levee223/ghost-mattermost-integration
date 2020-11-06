package app.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public final class Abbreviator {

    private Abbreviator() {
    }

    public static String abbreviate(final String text, final int maxLength, final List<String> keywords) {
        if (text.length() <= maxLength) {
            return text;
        }

        int minKeywordIndex = Integer.MAX_VALUE;
        for (final String keyword : keywords) {
            final int keywordIndex = text.indexOf(keyword);
            if (keywordIndex != -1) {
                minKeywordIndex = Math.min(minKeywordIndex, keywordIndex);
            }
        }
        if (minKeywordIndex == Integer.MAX_VALUE) {
            return StringUtils.abbreviate(text, maxLength);
        }
        return StringUtils.abbreviate(text, minKeywordIndex, maxLength);
    }

}
