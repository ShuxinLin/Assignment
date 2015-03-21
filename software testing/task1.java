/**
 * Created by susie on 18/04/15.
 */

import junit.runner.ClassPathTestCollector;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Vector;
import junit.runner.Sorter;

import static org.junit.Assert.*;

public class task1 {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // values=null,swapper=SOCC
    @Test
    public void Test1() {
        Vector inputVector = null;


        Vector expectedVector = null;

        // swapper = SOCC
        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=nothing,swapper=SOCC
    @Test
    public void Test2() {
        Vector inputVector = new Vector(0);

        Vector expectedVector = new Vector(0);


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=too long,0<=left<right<=length(values)-1,swapper=SOCC
    @Test
    public void Test3() {
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
        inputVector.addElement("too");
        inputVector.addElement("long");


        Vector expectedVector = new Vector(11);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("long");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");
        expectedVector.addElement("too");


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=single, 0<=left<right<=length(values)-1,swapper=SOCC
    @Test
    public void Test4() {
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
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");
        expectedVector.addElement("labs");


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 2, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple, 0<=left<right<=length(values)-1,swapper=null
    @Test
    public void Test5() {
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


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, null);


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple, 0<=left<right<=length(values)-1,swapper=CLOTLOV
    @Test
    public void Test6() {
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


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                    vector.addElement("changeLength");
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple, 0<=left<right<=length(values)-1,swapper=AS
    @Test
    public void Test7() {
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


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                String temp = (String) vector.elementAt(i);
                vector.setElementAt(vector.elementAt(i1), i);
                vector.setElementAt(temp, i1);
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple, 0<=left<right<=length(values)-1,swapper=NS
    @Test
    public void Test8() {
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


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                // do nothing
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple(double),0<=left<right<=length(values)-1,swapper=SOCC
    @Test
    public void Test9() {
        Vector inputVector = new Vector(12);
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
        inputVector.addElement("now");


        Vector expectedVector = new Vector(12);
        expectedVector.addElement("are");
        expectedVector.addElement("in");
        expectedVector.addElement("into");
        expectedVector.addElement("labs");
        expectedVector.addElement("looking");
        expectedVector.addElement("more");
        expectedVector.addElement("now");
        expectedVector.addElement("power");
        expectedVector.addElement("providing");
        expectedVector.addElement("technicians");
        expectedVector.addElement("the");
        expectedVector.addElement("the");


        Sorter.sortStrings(inputVector, 0, inputVector.size() - 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,left < right < 0 < length(values)-1,swapper=SOCC
    @Test
    public void Test10() {
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


        Sorter.sortStrings(inputVector, -2, -1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,left < 0 <= right <= length(values)-1,swapper=SOCC
    @Test
    public void Test11() {
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


        Sorter.sortStrings(inputVector, -1, inputVector.size() - 2, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,left < 0 < length(values) -1 < right,swapper=SOCC
    @Test
    public void Test12() {
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


        Sorter.sortStrings(inputVector, -2, inputVector.size() + 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,0 <= left <= length(values)-1 < right,swapper=SOCC
    @Test
    public void Test13() {
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


        Sorter.sortStrings(inputVector, 1, inputVector.size() + 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,0 < length(values)-1 < left < right,swapper=SOCC
    @Test
    public void Test14() {
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


        Sorter.sortStrings(inputVector, inputVector.size() + 1,inputVector.size() + 3, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if(((String)vector.elementAt(i)).compareTo((String)vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,right < 0 <= left <= length(values)-1,swapper=SOCC
    @Test
    public void Test15() {
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


        Sorter.sortStrings(inputVector, 1, -1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if (((String) vector.elementAt(i)).compareTo((String) vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,0 < =right < left <= length(values)-1,swapper=SOCC
    @Test
    public void Test16() {
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


        Sorter.sortStrings(inputVector, inputVector.size() - 2, 1, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if (((String) vector.elementAt(i)).compareTo((String) vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,0 <= right <= length(values)-1 < left,swapper=SOCC
    @Test
    public void Test17() {
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


        Sorter.sortStrings(inputVector, inputVector.size() + 2, inputVector.size() - 4, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if (((String) vector.elementAt(i)).compareTo((String) vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }

    // values=multiple,0 <= left = right <= length(values)-1,swapper=SOCC
    @Test
    public void Test18() {
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
        expectedVector.addElement("the");
        expectedVector.addElement("technicians");
        expectedVector.addElement("are");
        expectedVector.addElement("looking");
        expectedVector.addElement("into");
        expectedVector.addElement("providing");
        expectedVector.addElement("more");
        expectedVector.addElement("power");
        expectedVector.addElement("in");
        expectedVector.addElement("the");
        expectedVector.addElement("labs");


        Sorter.sortStrings(inputVector, inputVector.size() - 3, inputVector.size() - 3, new Sorter.Swapper() {
            @Override
            public void swap(Vector vector, int i, int i1) {
                if (((String) vector.elementAt(i)).compareTo((String) vector.elementAt(i1)) >= 0) {
                    String temp = (String) vector.elementAt(i);
                    vector.setElementAt(vector.elementAt(i1), i);
                    vector.setElementAt(temp, i1);
                }
            }
        });


        for (int i = 0; i < inputVector.size(); i++) {
            assertEquals(expectedVector.elementAt(i), inputVector.elementAt(i));
        }

    }
}
