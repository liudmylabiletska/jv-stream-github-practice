package practice;

import java.util.Arrays;
import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35; 
    private static final int MIN_YEARS_IN_UKR = 10;
    private static final String UKRAINIAN_NATIONALITY = "Ukrainian";
    private static final String PERIOD_SEPARATOR = ",";
    private static final String YEAR_SEPARATOR = "-";

    @Override
    public boolean test(Candidate candidate) {
        return candidate.getAge() >= MIN_AGE
                && candidate.isAllowedToVote()
                && UKRAINIAN_NATIONALITY.equals(candidate.getNationality())
                && livedInUkraineLongEnough(candidate.getPeriodsInUkr());
    }

    private boolean livedInUkraineLongEnough(String periodsInUkr) {
        if (periodsInUkr == null || periodsInUkr.isBlank()) {
            return false;
        }
        return Arrays.stream(periodsInUkr.split(PERIOD_SEPARATOR))
                .map(String::trim)
                .mapToInt(this::calculateYearsInPeriod)
                .sum() >= MIN_YEARS_IN_UKR;
    }

    private int calculateYearsInPeriod(String period) {
        String[] years = period.split(YEAR_SEPARATOR);
        if (years.length != 2) {
            return 0;
        }
        try {
            int startYear = Integer.parseInt(years[0].trim());
            int endYear = Integer.parseInt(years[1].trim());
            return endYear - startYear + 1;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Can't parse years from period: " + period, e);
        }
    }
}
