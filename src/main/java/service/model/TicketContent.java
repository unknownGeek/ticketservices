package service.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TicketContent {

   @NotNull
   @JsonProperty("ticketTitle")
   private String ticketTitle;

   @JsonProperty("description")
   private String description;

   @JsonProperty("acceptanceCriteria")
   private String acceptanceCriteria;
}

