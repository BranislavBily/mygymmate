//package Test.Workout;
//
//import db.DTO.Workout;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class WorkoutTest {
//
//    private static Workout workout;
//
//    public WorkoutTest() {
//
//    }
//
//    @BeforeClass
//    public static void beforeClass() {
//        System.out.println("Testing WorkoutTest");
//    }
//
//
//    @Before
//    public void before() {
//        workout = new Workout();
//    }
//
//    @Test
//    public void testGetSetID() {
//        System.out.println("Testing get set ID");
//        int id = 4;
//        workout.setId(id);
//        assertEquals(id, workout.getId());
//    }
//
//
//    @Test
//    public void testGetSetExercise() {
//        System.out.println("Testing get set Exercise");
//        String expected = "Push up";
//        workout.setExercise(expected);
//        assertEquals(expected, workout.getExercise());
//    }
//
//    @Test
//    public void testGetSetExerciseNull() {
//        System.out.println("Testing get set Exercise null");
//        String expected = null;
//        workout.setExercise(expected);
//        assertEquals(expected, workout.getExercise());
//    }
//
//    @Test
//    public void testGetSetRepetitions() {
//        System.out.println("Testing get set Repetitions");
//        int expected = 10;
//        workout.setRepetitions(10);
//        assertEquals(expected, workout.getRepetitions());
//    }
//
//    @Test
//    public void testGetSetWeight() {
//        System.out.println("Testing get set Weight");
//        String expected = "50.4";
//        workout.setWeight(expected);
//        assertEquals(expected, workout.getWeight());
//    }
//
//    @Test
//    public void testGetSetWeightNull() {
//        System.out.println("Testing get set Weight");
//        String expected = null;
//        workout.setWeight(expected);
//        assertEquals(expected, workout.getWeight());
//    }
//
//    @Test
//    public void testGetSetDate() {
//        System.out.println("Testing get set Date");
//        String expected = "2019-5-4";
//        workout.setDate(expected);
//        assertEquals(expected, workout.getDate());
//    }
//
//    @Test
//    public void testGetSetDateNull() {
//        System.out.println("Testing get set Date null");
//        String expected = null;
//        workout.setDate(expected);
//        assertEquals(expected, workout.getDate());
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        System.out.println("Stopped testing Workout");
//    }
//
//}