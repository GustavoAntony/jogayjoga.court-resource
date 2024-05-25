package jogayjoga.court;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "court")
@EqualsAndHashCode(of = "id")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Setter @Accessors(fluent=true, chain = true)
public class CourtModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_court")
    private String id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_address")
    private String address;

    @Column(name = "tx_capacity")
    private Integer capacity;

    @Column(name = "id_sport")
    private String sportId;

    @Column(name = "tx_reserved")
    private Integer isReserved;

    public void setReserveValue(Integer value){
        this.isReserved = value;
    }

    public CourtModel(Court in){
        this.id = in.id();
        this.name = in.name();
        this.address = in.address();
        this.capacity = in.capacity();
        this.sportId = in.sportId();
        this.isReserved = 0;
    }

    public Court to(){
        return new Court()
            .id(id)
            .name(name)
            .address(address)
            .capacity(capacity)
            .sportId(sportId)
            .isReserved(isReserved);
    }

}
