package service.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

   @JsonProperty("ticketId")
   private String ticketId;

   @JsonProperty("ticketContent")
   private TicketContent ticketContent;

   @JsonProperty("info")
   private TicketInfo info;

   @JsonProperty("people")
   private People people;

   @JsonProperty("createdDate")
   private Date createdDate;

   @JsonProperty("lastModifiedDate")
   private Date lastModifiedDate;
}


