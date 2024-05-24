package jogayjoga.court;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;

@Builder
@Getter @Setter @Accessors(fluent = true, chain = true)
@AllArgsConstructor @NoArgsConstructor
public class Court {
    
    private String id;
    private String name;
    private String address;
    private Integer capacity;
    private String sportId;
    private boolean isReserved;
}
