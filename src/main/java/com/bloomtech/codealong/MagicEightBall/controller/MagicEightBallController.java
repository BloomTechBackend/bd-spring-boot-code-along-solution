package com.bloomtech.codealong.MagicEightBall.controller;

import com.bloomtech.codealong.MagicEightBall.dao.MagicEightBallDao;
import com.bloomtech.codealong.MagicEightBall.types.MagicEightBallRequest;
import com.bloomtech.codealong.MagicEightBall.types.MagicEightBallResponse;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController                        // This class contains RESTful controllers
@RequestMapping(path="/magic8ball")    // Base path for all URLs this class handles is "/Magic8Ball"
public class MagicEightBallController {

    // Instantiate the Dao to be used to acquire data
    private MagicEightBallDao theEightBall = new MagicEightBallDao();

    /**
     * Handle HTTP POST requests for responses to questions
     *        with the URL: /magic8Ball/ask
     *
     * HTTP POST requests provide data for the request as JSON in the body of the request
     * The @RequestBody annotation tells Spring Boot to convert the JSON in the body to
     *     an object of the class specified
     *
     * @param theRequest - Will contain the JSON data passed through the request body
     * @return           - A Magic8BallResponse object
     */
    @PostMapping(value="/ask")
    public MagicEightBallResponse getResponse(@RequestBody MagicEightBallRequest theRequest) {
           // Log the request to the server log
           logRequest("Request received via HTTP POST URL: /ask and question: " + theRequest.getQuestion());

           // Return a new response object with the question asked and answer from the Magic Eight Ball
            return new MagicEightBallResponse(theRequest.getQuestion() ,theEightBall.getResponse());
    }
    /**
     * Handle HTTP Get requests for responses to questions
     *        with the URL: /magic8Ball/ask?question="value"
     *
     * HTTP GET requests provide data for the request as a value in in the URL
     * The @RequestParam annotation tells Spring Boot to convert the request parameter name in the URL to
     *     an object of the class specified
     *
     * @param question - Will contain the question to be asked as a query parameter
     * @return         - A Magic8BallResponse object
     */
    @GetMapping (value="/ask")
    public MagicEightBallResponse getResponse(@RequestParam String question) {
        // Log the request to the server log
        logRequest("Request received via HTTP POST URL: /ask and question: " + question);

        // Return a new response object with the question asked and answer from the Magic Eight Ball
        return new MagicEightBallResponse(question ,theEightBall.getResponse());
    }
    /*****************************************************************************************
     * Helper method to log a message provided via parameter ane timestamp
     *
     * @param message
     */
    private void logRequest(String message) {
            Long datetime = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(datetime);
            System.out.println(new Timestamp(datetime) + "\t--> " + message);
    }
}
