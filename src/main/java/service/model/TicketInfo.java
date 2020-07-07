package service.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo {

   @JsonProperty("ticketStatus")
   private String ticketStatus;

   @JsonProperty("ticketType")
   private String ticketType;

   @JsonProperty("priority")
   private String priority;
}

