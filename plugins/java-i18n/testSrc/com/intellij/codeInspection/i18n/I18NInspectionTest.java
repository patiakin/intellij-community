/*
 * Copyright (c) 2005 JetBrains s.r.o. All Rights Reserved.
 */
package com.intellij.codeInspection.i18n;

import com.intellij.openapi.application.PluginPathManager;
import com.intellij.openapi.roots.LanguageLevelProjectExtension;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.impl.JavaPsiFacadeEx;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;


public class I18NInspectionTest extends LightJavaCodeInsightFixtureTestCase {

  I18nInspection myTool = new I18nInspection();
  
  private void doTest() {
    myFixture.enableInspections(myTool);
    myFixture.testHighlighting("i18n/" + getTestName(false) + ".java");
  }

  public void testHardCodedStringLiteralAsParameter() { doTest(); }
  public void testReturnTypeInheritsNonNlsAnnotationFromParent() { doTest(); }
  public void testRecursiveInheritance() { doTest(); }
  public void testParameterInheritsNonNlsAnnotationFromSuper() { doTest(); }
  public void testLocalVariables() { doTest(); }
  public void testFields() { doTest(); }
  public void testInAnnotationArguments() { doTest(); }
  public void testAnonymousClassConstructorParameter() { doTest(); }
  public void testStringBufferNonNls() { doTest(); }
  public void testEnum() {
     final JavaPsiFacade facade = JavaPsiFacadeEx.getInstanceEx(getProject());
     final LanguageLevel effectiveLanguageLevel = LanguageLevelProjectExtension.getInstance(facade.getProject()).getLanguageLevel();
     LanguageLevelProjectExtension.getInstance(facade.getProject()).setLanguageLevel(LanguageLevel.JDK_1_5);
     try {
       doTest();
     }
     finally {
       LanguageLevelProjectExtension.getInstance(facade.getProject()).setLanguageLevel(effectiveLanguageLevel);
     }
   }

  public void testVarargNonNlsParameter() { doTest(); }
  public void testInitializerInAnonymousClass() { doTest(); }
  public void testNonNlsArray() { doTest(); }
  public void testNonNlsEquals() { doTest(); }
  public void testParameterInNewAnonymousClass() { doTest(); }
  public void testConstructorCallOfNonNlsVariable() { doTest(); }
  public void _testConstructorChains() { doTest(); }
  public void testSwitchOnNonNlsString() { doTest(); }
  public void testNonNlsComment() {
    myTool.nonNlsCommentPattern = "MYNON-NLS";
    myTool.cacheNonNlsCommentPattern();
    doTest();
  }
  public void testAnnotationArgument() { doTest(); }
  public void testAssertionStmt() { doTest(); }
  public void testExceptionCtor() { doTest(); }
  public void testSpecifiedExceptionCtor() {
    boolean old = myTool.ignoreForExceptionConstructors;
    try {
      myTool.ignoreForSpecifiedExceptionConstructors = "java.io.IOException";
      myTool.ignoreForExceptionConstructors = false;
      doTest();
    }
    finally {
      myTool.ignoreForSpecifiedExceptionConstructors = "";
      myTool.ignoreForExceptionConstructors = old;
    }
  }

  public void testEnumConstantIgnored() {
    boolean oldState = myTool.setIgnoreForEnumConstants(true);
    try {
      doTest();
    }
    finally {
      myTool.setIgnoreForEnumConstants(oldState);
    }
  }

  @Override
  protected String getTestDataPath() {
    return PluginPathManager.getPluginHomePath("java-i18n") + "/testData/inspections";
  }
}
