package Assignment;

@RestController
@RequestMapping("/api")
public class FootballController {
    private final FootballService footballService;

    public FootballController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/drawn-matches")
    public int getDrawnMatchesCount(@RequestParam("year") int year) {
        return footballService.getDrawnMatchesCount(year);
    }
}
