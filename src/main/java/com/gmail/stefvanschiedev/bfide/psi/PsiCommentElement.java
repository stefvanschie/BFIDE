package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a comment in BrainFuck. Every character that doesn't match any of the instruction characters will be a
 * comment.
 */
public class PsiCommentElement extends PsiElement {

    private String comment;

    public PsiCommentElement(TextRange range, PsiElement parent, String comment) {
        super(range, parent);

        this.comment = comment;
    }

    @Override
    public String toString() {
        return comment;
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiCommentElement> {

        private Pattern pattern = Pattern.compile("[^><+\\-.,\\[\\]]+");

        @Override
        public boolean isParsable(String text) {
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                if (matcher.start() == 0)
                    return true;
            }

            return false;
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                if (matcher.start() == 0) {
                    parent.addChild(new PsiCommentElement(new TextRange(offset, offset + matcher.end()), parent,
                            text.substring(0, matcher.end())));
                    return matcher.end();
                }
            }

            throw new IllegalStateException("Unexpected end of method");
        }
    }
}