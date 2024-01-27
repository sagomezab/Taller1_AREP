package edu.escuelaing.arem.ASE.app;


import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{

     public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Compara la respuesta de la pagina con la que arroja la API
     */
    public void testAppAPI(){
        HashMap<String, String> answers = new HashMap<>();
        answers.put("Jhon","{\"Title\":\"Jhon Jatenjor's Interviews\",\"Year\":\"2021–\",\"Rated\":\"N/A\",\"Released\":\"N/A\",\"Runtime\":\"N/A\",\"Genre\":\"Talk-Show\",\"Director\":\"N/A\",\"Writer\":\"N/A\",\"Actors\":\"Jean Cárdenas, Mary Workman, Grant Workman\",\"Plot\":\"Jhon Jatenjor's Interviews is a Colombian television program with emphasis to stand out in the international environment on interviews to film celebrities, through simultaneous translations. The purpose of this digital and physica...\",\"Language\":\"English, Spanish, Japanese, Russian\",\"Country\":\"Colombia\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BODhmMTE3ZjctOTMwNy00YmQzLWJkMDEtZmU0NDYwMmY1NmU2XkEyXkFqcGdeQXVyMTM5MzA5MDA1._V1_SX300.jpg\",\"Ratings\":[],\"Metascore\":\"N/A\",\"imdbRating\":\"N/A\",\"imdbVotes\":\"21\",\"imdbID\":\"tt21241550\",\"Type\":\"series\",\"totalSeasons\":\"N/A\",\"Response\":\"True\"}");
        ArrayList<ThreadsTest> threadTests = new ArrayList<>();
        threadTests.add(new ThreadsTest("Jhon"));

        for(ThreadsTest threadTest: threadTests){
            threadTest.run();
        }
        for(ThreadsTest threadsTest: threadTests){
            try {
                threadsTest.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(ThreadsTest threadTest: threadTests){
            assertEquals(answers.get(threadTest.getTitle()), threadTest.getAnswer());
        }
    }

    /**
     * Compara el tamaño del cache al consultarsen varias peliculas, para ver que no se dupliquen
     */
    public void testAppAPICache(){
        HttpServer.cache.clear();
        ArrayList<ThreadsTest> threadTests = new ArrayList<>();
        threadTests.add(new ThreadsTest("Avengers: Endgame"));
        threadTests.add(new ThreadsTest("Avengers: Endgame"));
        threadTests.add(new ThreadsTest("Guardians of the Galaxy"));
        threadTests.add(new ThreadsTest("Avengers: Endgame"));
        threadTests.add(new ThreadsTest("Guardians of the Galaxy"));

        for(ThreadsTest threadTest: threadTests){
            threadTest.run();
        }
        for(ThreadsTest threadsTest: threadTests){
            try {
                threadsTest.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        assertEquals(2,HttpServer.cache.size());

    }
}
