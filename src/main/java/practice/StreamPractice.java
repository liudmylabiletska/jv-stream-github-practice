package practice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import model.Candidate;
import model.Cat;
import model.Person;

public class StreamPractice {

    public int findMinEvenNumber(List<String> numbers) {
        if (numbers == null) {
            throw new RuntimeException("Can't get min value from list: " + numbers);
        }
        return numbers.stream()
                .flatMap(s -> Stream.of(s.split(",")))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .filter(num -> num % 2 == 0)
                .min()
                .orElseThrow(() -> new RuntimeException("Can't get min value from list: "
                        + numbers));
    }

    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(i -> i % 2 != 0 ? numbers.get(i) - 1 : numbers.get(i))
                .filter(num -> num % 2 != 0)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Person> selectMenByAge(List<Person> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                    .filter(person -> person.getSex() == Person.Sex.MAN)
                    .filter(person -> person.getAge() >= fromAge && person.getAge() <= toAge)
                    .toList();
    }

    public List<Person> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<Person> peopleList) {
        return peopleList.stream()
                    .filter(person -> {
                        if (person.getSex() == Person.Sex.MAN) {
                            return person.getAge() >= fromAge && person.getAge() <= maleToAge;
                        } else {
                            return person.getAge() >= fromAge && person.getAge() <= femaleToAge;
                        }
                    })
                    .toList();
    }

    public List<String> getCatsNames(List<Person> peopleList, int femaleAge) {
        return peopleList.stream()
                    .filter(person -> person.getSex() == Person.Sex.WOMAN)
                    .filter(person -> person.getAge() >= femaleAge)
                    .flatMap(person -> person.getCats().stream())
                    .map(Cat::getName)
                    .toList();
    }

    public List<String> validateCandidates(List<Candidate> candidates) {
        CandidateValidator validator = new CandidateValidator();
        return candidates.stream()
                    .filter(validator)
                    .map(Candidate::getName)
                    .sorted()
                    .toList();
    }
}
