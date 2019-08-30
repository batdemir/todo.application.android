package com.batdemir.android.todolist.application.android;

import com.batdemir.android.todolist.application.android.Tools.EditTextTools.ToolEditText;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailValidationTest {
    @Test
    public void emailValidation_CorrectEmailSimple_ReturnsTrue(){
        assertTrue(new ToolEditText().isEmailValid("batuhandemirp@gmail.com"));
    }

    @Test
    public void emailValidation_CorrectEmailSubDmain_ReturnsTrue(){
        assertTrue(new ToolEditText().isEmailValid("batuhandemirp@gmail.com.tr"));
    }

    @Test
    public void emailValidation_InvalidEmailNoTld_ReturnsFalse(){
        assertFalse(new ToolEditText().isEmailValid("batuhandemirp@gmail"));
    }

    @Test
    public void emailValidation_InvalidEmailDoubleDot_ReturnsFalse(){
        assertFalse(new ToolEditText().isEmailValid("batuhandemirp@gmail..com"));
    }

    @Test
    public void emailValidation_InvalidEmailNoName_ReturnsFalse(){
        assertFalse(new ToolEditText().isEmailValid("@gmail.com"));
    }

    @Test
    public void emailValidation_EmptyString_ReturnsFalse(){
        assertFalse(new ToolEditText().isEmailValid(""));
    }

    @Test
    public void emailValidation_Null_ReturnsFalse(){
        assertFalse(new ToolEditText().isEmailValid(""));
    }
}
