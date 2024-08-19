package Newjeanse.summer.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter @Setter
@Document(collection = "bills")
public class Bills {
    @Id
    private String id;
    private String userId;
    private String name;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private LocalDate dueDate;
    private int amountDue;
    private int powerUsage;
}
