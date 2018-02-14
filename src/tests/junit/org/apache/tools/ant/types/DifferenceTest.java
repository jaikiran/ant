package org.apache.tools.ant.types;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.resources.Difference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class DifferenceTest {

    private Project project;

    @Before
    public void setUp() {
        project = new Project();
    }

    @Test
    public void testPathDifference() {
        final Path destPath = new Path(project, "dest");
        final Path classPath = new Path(project, "cp");
        final Difference diff = new Difference();
        diff.add(destPath);
        diff.add(classPath);
        final Path pathUsingDiff = new Path(project);
        pathUsingDiff.add(diff);
        System.out.println("Path using difference " + pathUsingDiff);

        final String[] classPathElements = classPath.list();
        final String[] destPathElements = destPath.list();
        String checkPath = "";
        for (int i = 0; i < classPathElements.length; ++i) {
            String element = classPathElements[i];
            boolean inDestPath = false;
            for (int j = 0; j < destPathElements.length && !inDestPath; ++j) {
                inDestPath = destPathElements[j].equals(element);
            }
            if (!inDestPath) {
                if (checkPath.length() == 0) {
                    checkPath = element;
                } else {
                    checkPath += ":" + element;
                }
            }
        }
        System.out.println("Path using older logic " + checkPath);
        Assert.assertEquals("Unexpected path created using newer Difference logic", checkPath.toString(), pathUsingDiff.toString());

    }
}
