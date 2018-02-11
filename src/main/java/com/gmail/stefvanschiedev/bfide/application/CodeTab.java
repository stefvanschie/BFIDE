package com.gmail.stefvanschiedev.bfide.application;

import com.gmail.stefvanschiedev.bfide.file.PsiFile;
import com.gmail.stefvanschiedev.bfide.psi.*;
import com.gmail.stefvanschiedev.bfide.psi.element.PsiCommentElement;
import com.gmail.stefvanschiedev.bfide.psi.element.PsiDecrementPointerElement;
import com.gmail.stefvanschiedev.bfide.psi.element.PsiIncrementPointerElement;
import com.gmail.stefvanschiedev.bfide.psi.element.PsiLoopElement;
import javafx.scene.control.Tab;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Represents an opened tab in which code is present
 */
public class CodeTab extends Tab {

    private PsiFile file;

    public CodeTab(PsiFile file) {
        this.file = file;

        setText(file.getName());

        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        try {
            codeArea.replaceText(0,0, new String(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContent(codeArea);

        codeArea.setOnKeyTyped(event -> {
            file.parse(codeArea.getText());
            codeArea.clearStyle(0, codeArea.getText().length() - 1);
            updateStyles(codeArea);
        });

        updateStyles(codeArea);
    }

    private void updateStyles(CodeArea codeArea) {
        Map<TextRange, String> styles = getStyles();

        styles.forEach((range, style) -> codeArea.setStyleClass(range.getStart(), range.getLength(), style));
    }

    /**
     * Returns a map of styles for the specified ranges
     *
     * @return a map with styles
     */
    private Map<TextRange, String> getStyles() {
        Map<TextRange, String> map = new HashMap<>();

        for (PsiElement element : file.allChildren()) {
            if (element instanceof PsiLoopElement) {
                map.put(new TextRange(element.getTextRange().getStart(), element.getTextRange().getStart() + 1),
                        "green");
                map.put(new TextRange(element.getTextRange().getLength() + 1,
                        element.getTextRange().getLength() + 2), "green");
            } else if (element instanceof PsiIncrementPointerElement || element instanceof PsiDecrementPointerElement)
                map.put(element.getTextRange(), "blue");
             else if (element instanceof PsiCommentElement)
                 map.put(element.getTextRange(), "comment");
             else
                map.put(element.getTextRange(), "green");
        }

        return map;
    }
}
