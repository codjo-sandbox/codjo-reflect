/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.reflect.collect;
import junit.framework.TestCase;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 * Class de test de <code>JarIterator</code>
 *
 * @author Boris
 * @version $Revision: 1.1.1.1 $
 */
public class JarIteratorTest extends TestCase {
    private String javaJarFile;


    public void test_javaAppletFramework() throws Exception {
        List<String> expectedClassFiles = new ArrayList<String>();
        expectedClassFiles.add("org/junit/After.class");
        expectedClassFiles.add("org/junit/AfterClass.class");

        List<String> foundClasFiles = new ArrayList<String>();
        for (Iterator iter = new JarIterator(javaJarFile); iter.hasNext();) {
            String obj = (String)iter.next();
            if (obj.startsWith("org/junit/After")) {
                foundClasFiles.add(obj);
            }
        }

        Collections.sort(expectedClassFiles);
        Collections.sort(foundClasFiles);
        assertEquals(expectedClassFiles, foundClasFiles);
    }


    public void test_ressource() throws Exception {
        List<String> foundClasFiles = new ArrayList<String>();
        for (Iterator iter = new JarIterator(javaJarFile); iter.hasNext();) {
            String obj = (String)iter.next();
            if (obj.endsWith("MANIFEST.MF")) {
                foundClasFiles.add(obj);
            }
        }

        assertEquals("Le fichier est trouve ! ", 1, foundClasFiles.size());
    }


    @Override
    protected void setUp() throws java.lang.Exception {
        URL url = TestCase.class.getResource("/org/junit/runner/JUnitCore.class");
        String urlStr = url.toString();
        int from = "jar:file:".length();
        int to = urlStr.indexOf("!/");
        javaJarFile = urlStr.substring(from, to);
    }
}
