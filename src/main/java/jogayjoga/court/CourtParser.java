package jogayjoga.court;

public class CourtParser {
    public static Court to(CourtIn in ){
        return Court.builder()
            .name(in.name())
            .address(in.address())
            .capacity(in.capacity())
            .sportId(in.sportId())
            .build();
    }

    public static CourtOut to(Court in){
        return CourtOut.builder()
            .id(in.id())
            .name(in.name())
            .address(in.address())
            .build();
    }
}
