package com.firstProject.service;

import com.firstProject.model.Option;
import com.firstProject.model.Question;
import com.firstProject.model.QuestionOptionRequest;
import com.firstProject.model.QuestionOptionResponse;
import com.firstProject.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollQuestionServiceImpl implements PollQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PollOptionService optionService;


    @Override
    public QuestionOptionResponse createQuestion(QuestionOptionRequest questionOptionRequest) {
        if (questionOptionRequest.getQuestion() != null &&
                isValidOption(questionOptionRequest.getOption1()) &&
                isValidOption(questionOptionRequest.getOption2()) &&
                isValidOption(questionOptionRequest.getOption3()) &&
                isValidOption(questionOptionRequest.getOption4())) {

            Long questionId = questionRepository.createQuestion(questionOptionRequest.toQuestion(questionOptionRequest.getQuestion()));
            ArrayList<Option> answers = new ArrayList<>();
            answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption1(), questionId)));
            answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption2(), questionId)));
            answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption3(), questionId)));
            answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption4(), questionId)));

            Question question = questionRepository.getQuestionById(questionId);
            return question.toQuestionOptionResponse(question, answers);
        } else {
            throw new IllegalArgumentException("Question and all four options are required.");
        }
    }

    private boolean isValidOption(Option option) {
        return option != null && option.getTextOption() != null && !option.getTextOption().isEmpty();

    }

    @Override
    public Question updateQuestion(Question question) {
        questionRepository.updateQuestion(question);
        return questionRepository.getQuestionById(question.getId());
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteQuestion(id);

    }

    @Override
    public QuestionOptionResponse getQuestionById(Long id) {
        List<Option> options = optionService.getOptionsByQuestionId(id);
        Question question = questionRepository.getQuestionById(id);
        return question.toQuestionOptionResponse(question, options);
    }

    @Override
    public List<QuestionOptionResponse> getAllPoll() {
        List<QuestionOptionResponse> responses = new ArrayList<>();
        List<Question> questions = questionRepository.getAllPoll();

        // אם אין שאלות במערכת, יצור שאלות מראש
        if (questions.isEmpty()) {
            List<QuestionOptionRequest> list = createQuestionsAndAnswers();
            for (QuestionOptionRequest request : list) {
                createQuestion(request);
            }
        }

        // שחזור כל השאלות עם האפשרויות שלהן
        for (Question question : questions) {
            List<Option> options = optionService.getOptionsByQuestionId(question.getId());
            responses.add(question.toQuestionOptionResponse(question, options));
        }
        return responses;
    }

    private List<QuestionOptionRequest> createQuestionsAndAnswers() {
        List<QuestionOptionRequest> list = new ArrayList<>();

        Question question1 = new Question(null, "Between the following, what do you most love to do?");
        Option option1 = new Option(null, "Watch TV", null);
        Option option2 = new Option(null, "Play the computer", null);
        Option option3 = new Option(null, "Hanging out with friends", null);
        Option option4 = new Option(null, "Travel the world", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "Where is your preferred place to travel?");
        option1 = new Option(null, "USA", null);
        option2 = new Option(null, "France", null);
        option3 = new Option(null, "South America", null);
        option4 = new Option(null, "Thailand", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "What's your favorite season?");
        option1 = new Option(null, "Spring", null);
        option2 = new Option(null, "Summer", null);
        option3 = new Option(null, "Autumn ", null);
        option4 = new Option(null, "Winter", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "Which type of cuisine do you prefer?");
        option1 = new Option(null, "Italian", null);
        option2 = new Option(null, "Mexican", null);
        option3 = new Option(null, "Indian", null);
        option4 = new Option(null, "Japanese", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "What's your favorite outdoor activity?");
        option1 = new Option(null, "Hiking", null);
        option2 = new Option(null, "Cycling", null);
        option3 = new Option(null, "Swimming", null);
        option4 = new Option(null, "Picnicking", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        return list;
    }
}