package springbootstarter.service;

import java.util.Set;

import springbootstarter.dto.WordCount;

public interface IParagraphService {
	Set<WordCount> findWordCount(String paragraph);
}
