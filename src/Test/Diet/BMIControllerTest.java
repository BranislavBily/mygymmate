/*package Test.Diet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sample.Controllers.FragmentControllers.DietSceneFragments.BMIFragmentController;

import static org.junit.Assert.assertEquals;

public class BMIControllerTest {

    private BMIFragmentController bmiFragmentController;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing BMIFragmentController");
    }

    //Initialize new BMIFragmentController, so there is no way methods will cause any harm to each other
    @Before
    public void before() {
        bmiFragmentController = new BMIFragmentController();
    }

    @Test
    public void testingCalculation() {
        System.out.println("Testing calculation");
        double expectedBMI = 22.2;
        assertEquals(expectedBMI, bmiFragmentController.getMyBMI(80, 190), 0.05);
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Stopped testing BMIFragmentController");
    }
}
*/