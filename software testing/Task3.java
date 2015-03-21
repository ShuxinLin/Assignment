import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;


public class Task3 {
    
    @Test
    public void testMutantsExpected1()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar1.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }
    
    @Test
    public void testMutantsEmptyStringVector1()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar1.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsExpected2()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar2.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }
    
    // Added to find mutation 2
    @Test
    public void additionalTestForM2 ()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar2.sortStrings(inputVector, -1, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    @Test
    public void testMutantsEmptyStringVector2()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar2.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsExpected3()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar3.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsEmptyStringVector3()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar3.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsExpected4()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar4.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsEmptyStringVector4()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar4.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsExpected5()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar5.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsEmptyStringVector5()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar5.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsExpected6()
    {
        Vector inputVector = new Vector(11);
        inputVector.addElement("the");
        inputVector.addElement("technicians");
        inputVector.addElement("are");
        inputVector.addElement("looking");
        inputVector.addElement("into");
        inputVector.addElement("providing");
        inputVector.addElement("more");
        inputVector.addElement("power");
        inputVector.addElement("in");
        inputVector.addElement("the");
        inputVector.addElement("labs");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        sortStringsVar6.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }

    @Test
    public void testMutantsEmptyStringVector6()
    {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        sortStringsVar6.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < expectedVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }
    }
}
