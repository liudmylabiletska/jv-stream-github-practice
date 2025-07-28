package core.basesyntax;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import model.Candidate;
import model.Person;
import practice.StreamPractice;

public class Main {
    public static void main(String[] args) {
        StreamPractice solution = new StreamPractice();

        System.out.println("--- StreamPractice Examples ---");

        List<String> numbersInput = List.of("12,11,5", "1,22,757", "71", "39,31,55,148",
                "3,2,2,5", "27,44,89", "12,11,5", "64,22,757");
        try {
            int minEven = solution.findMinEvenNumber(numbersInput);
            System.out.println("Min even number from " + numbersInput + ": " + minEven);
        } catch (RuntimeException e) {
            System.err.println("Error finding min even number: " + e.getMessage());
        }

        List<String> noEvenNumbers = List.of("1,3,5", "7,9");
        try {
            int minEven = solution.findMinEvenNumber(noEvenNumbers);
            System.out.println("Min even number from " + noEvenNumbers + ": " + minEven);
        } catch (RuntimeException e) {
            System.err.println("Error finding min even number (no even found): " + e.getMessage());
        }

        List<Integer> digitsForAverage = Arrays.asList(6, 2, 3, 7, 2, 5);
        try {
            Double oddAverage = solution.getOddNumsAverage(digitsForAverage);
            System.out.println("Average of odd numbers from " + digitsForAverage
                    + " (with transformation): " + oddAverage);
        } catch (NoSuchElementException e) {
            System.err.println("Error getting odd numbers average: " + e.getMessage());
        }

        List<Integer> noOddNumbersAfterTransform = Arrays.asList(2, 4, 6, 8, 10);
        try {
            Double oddAverage = solution.getOddNumsAverage(noOddNumbersAfterTransform);
            System.out.println("Average of odd numbers from " + noOddNumbersAfterTransform
                    + " (with transformation): " + oddAverage);
        } catch (NoSuchElementException e) {
            System.err.println("Error getting odd numbers average "
                    + "(no odd found after transformation): "
                    + e.getMessage());
        }

        List<Person> samplePeople = List.of(
                new Person("Alice", 20, Person.Sex.WOMAN),
                new Person("Bob", 25, Person.Sex.MAN),
                new Person("Charlie", 30, Person.Sex.MAN),
                new Person("Diana", 40, Person.Sex.WOMAN),
                new Person("Eve", 17, Person.Sex.WOMAN),
                new Person("Frank", 27, Person.Sex.MAN)
        );

        List<Person> selectedMen = solution.selectMenByAge(samplePeople, 20, 27);
        System.out.println("Men aged 20–27: " + selectedMen);

        Candidate validCandidate = new Candidate(38, "Ukrainian", true, "2000-2015");
        validCandidate.setName("Valid Bob");
        Candidate youngCandidate = new Candidate(30, "Ukrainian", true, "2000-2015");
        youngCandidate.setName("Young Alice");
        Candidate foreignCandidate = new Candidate(40, "German", true, "2000-2015");
        foreignCandidate.setName("Foreign Karl");
        Candidate notVotedCandidate = new Candidate(45, "Ukrainian", false, "2000-2015");
        notVotedCandidate.setName("Not Voted Mike");
        Candidate lessYearsCandidate = new Candidate(36, "Ukrainian", true, "2010-2015");
        lessYearsCandidate.setName("Less Years Sarah");

        List<Candidate> candidates = List.of(validCandidate, youngCandidate, foreignCandidate,
                notVotedCandidate, lessYearsCandidate);
        List<String> eligibleCandidates = solution.validateCandidates(candidates);
        System.out.println("Eligible candidates for president: " + eligibleCandidates);
    }
}
