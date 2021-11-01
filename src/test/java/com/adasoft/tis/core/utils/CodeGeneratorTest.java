package com.adasoft.tis.core.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class CodeGeneratorTest {

    private CodeGenerator codeGenerator = new CodeGenerator();

    @Test
    public void generateLetterCode4Interval2() {
        String code = codeGenerator.generateLetterCode(4, 2, '-');
        assertLinesMatch(List.of("^[a-z]{2}-[a-z]{2}$"), List.of(code));
    }

    @Test
    public void generateLetterCode9Interval3() {
        String code = codeGenerator.generateLetterCode(9, 3, '-');
        assertLinesMatch(List.of("^[a-z]{3}-[a-z]{3}-[a-z]{3}$"), List.of(code));
    }

}