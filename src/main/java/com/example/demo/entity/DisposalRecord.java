@Entity
public class LifecycleEvent {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private Asset asset;

private String eventType;
private String eventDescription;

private LocalDateTime eventDate;

@ManyToOne
private User performedBy;

@PrePersist
public void prePersist() {
eventDate = LocalDateTime.now();
}
}