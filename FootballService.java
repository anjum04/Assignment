package Assignment;

@Service
public class FootballService {
    private static final String API_URL = "https://jsonmock.hackerrank.com/api/football_matches?year=%d&page=%d";

    private final RestTemplate restTemplate;

    public FootballService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getDrawnMatchesCount(int year) {
        int drawnMatchesCount = 0;
        int page = 1;
        boolean hasNextPage = true;

        while (hasNextPage) {
            String url = String.format(API_URL, year, page);
            ResponseEntity<MatchesResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<MatchesResponse>() {});

            MatchesResponse matchesResponse = response.getBody();
            List<Match> matches = matchesResponse.getData();

            for (Match match : matches) {
                if (match.getTeam1goals() == match.getTeam2goals()) {
                    drawnMatchesCount++;
                }
            }

            hasNextPage = matchesResponse.getTotalPages() > page;
            page++;
        }

        return drawnMatchesCount;
    }
}
