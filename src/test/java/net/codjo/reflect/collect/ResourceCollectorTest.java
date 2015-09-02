/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.reflect.collect;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Class de test de <code>ResourceCollector</code>
 *
 * @author Boris
 * @version $Revision: 1.2 $
 */
public class ResourceCollectorTest extends AbstractCollectorTest {
    public void test_addFilter() throws Exception {
        List<String> expectedResourcesFiles = new ArrayList<String>();
        expectedResourcesFiles.add("/META-INF/MANIFEST.MF");

        ResourceCollector collector = new ResourceCollector(JUnitCore.class);
        collector.addResourceFilter(new FakeResourceFilter());
        List result = Arrays.asList(collector.collect());

        assertContains(expectedResourcesFiles, result);
    }


    public void test_addClassFilter() throws Exception {
        List<String> expectedResourcesFiles = new ArrayList<String>();
        expectedResourcesFiles.add("/org/junit/After.class");
        expectedResourcesFiles.add("/org/junit/AfterClass.class");

        ResourceCollector collector = new ResourceCollector(JUnitCore.class);
        collector.setExcludeClassFile(false);
        collector.addResourceFilter(new FakeResourceClassFilter());
        List<String> result = Arrays.asList(collector.collect());

        assertContains(expectedResourcesFiles, result);
    }


    private void assertContains(List<String> expectedResourcesFiles, List result) {
        expectedResourcesFiles.removeAll(result);
        assertEquals(0, expectedResourcesFiles.size());
    }


    static class FakeResourceFilter implements ResourceFilter {
        public boolean accept(String resourceName) {
            return resourceName.startsWith("/META-INF") && resourceName.endsWith(".MF");
        }
    }

    static class FakeResourceClassFilter implements ResourceFilter {
        public boolean accept(String resourceName) {
            return resourceName.startsWith("/org/junit/After") && resourceName.endsWith(".class");
        }
    }
}
