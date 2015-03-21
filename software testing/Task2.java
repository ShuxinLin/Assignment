import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;


public class Task2 {

    @Test
    public void TestEmptyStringVector() {
        Vector inputVector = new Vector(1);
        inputVector.addElement("");

        Vector expectedVector = new Vector(1);
        expectedVector.addElement("");

        Sorter.sortStrings(inputVector, 0, 0, new Sorter.Swapper() {
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
