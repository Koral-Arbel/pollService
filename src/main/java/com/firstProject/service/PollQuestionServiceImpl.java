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
        if (questionOptionRequest.getQuestion() != null) {
            Long questionId = questionRepository.createQuestion(questionOptionRequest.toQuestion(questionOptionRequest.getQuestion()));
            ArrayList<Option> answers = new ArrayList<>();
            if (questionOptionRequest.getOption1() != null)
                answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption1(), questionId)));
            if (questionOptionRequest.getOption2() != null)
                answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption2(), questionId)));
            if (questionOptionRequest.getOption3() != null)
                answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption3(), questionId)));
            if (questionOptionRequest.getOption4() != null)
                answers.add(optionService.createOption(questionOptionRequest.toOptions(questionOptionRequest.getOption4(), questionId)));

            Question question = questionRepository.getQuestionById(questionId);
            return question.toQuestionOptionResponse(question, answers);
        } else {
            return null;
        }
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
        List<Option> answers = optionService.getOptionsByQuestionId(id);
        Question question = questionRepository.getQuestionById(id);
        return question.toQuestionOptionResponse(question, answers);
    }

    @Override
    public List<QuestionOptionResponse> getQuestionsList() {
        List<QuestionOptionResponse> responses = new ArrayList<>();
        List<Question> questions = questionRepository.getQuestionsList();
        if (questions.size() == 0) {
            List<QuestionOptionRequest> list = createQuestionsAndAnswers();
            for (int i = 0; i < list.size(); i++) {
                createQuestion(list.get(i));
            }
            getQuestionsList();
        }
        for (int i = 0; i < questions.size(); i++) {
            List<Option> options = optionService.getOptionsByQuestionId(questions.get(i).getId());
            responses.add(questions.get(i).toQuestionOptionResponse(questions.get(i), options));
        }
        return responses;
    }

    private List<QuestionOptionRequest> createQuestionsAndAnswers() {
        List<QuestionOptionRequest> list = new ArrayList<>();

        Question question1 = new Question(null, "If you had to listen to one album for the rest of your life, what would it be?");
        Option option1 = new Option(null, "Suicide", null);
        Option option2 = new Option(null, "Dónde Están los Ladrones", null);
        Option option3 = new Option(null, "Nick of Time", null);
        Option option4 = new Option(null, "Heart Like a Wheel", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "What's another language you wish you could speak fluently without studying?");
        option1 = new Option(null, "Chinese", null);
        option2 = new Option(null, "Ukrainian", null);
        option3 = new Option(null, "Spanish", null);
        option4 = new Option(null, "Japanese", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "What is your favorite flower?");
        option1 = new Option(null, "Daisy", null);
        option2 = new Option(null, "Apricot", null);
        option3 = new Option(null, "Rose ", null);
        option4 = new Option(null, "Orchid", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "Which mythical creature would make the best pet?");
        option1 = new Option(null, "Dragon", null);
        option2 = new Option(null, "Unicorn", null);
        option3 = new Option(null, "Goblin", null);
        option4 = new Option(null, "Sphinx", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        question1 = new Question(null, "What wild animals suit you most?");
        option1 = new Option(null, "Elephant", null);
        option2 = new Option(null, "Tiger", null);
        option3 = new Option(null, "Giraffe", null);
        option4 = new Option(null, "whale", null);
        list.add(new QuestionOptionRequest(question1, option1, option2, option3, option4));

        return list;
    }
}